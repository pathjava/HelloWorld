// Oleg Kiselev
// 06.07.2020, 19:03

package ru.progwards.java2.lessons.http;

import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.AccountService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AtmClient implements AccountService {

    private static final int PORT_ID = 45000;
    private static final String HOST_NAME = "localhost";
    private String getRequest;

    private void client() {
        try (Socket socket = new Socket(HOST_NAME, PORT_ID)) {
            try (InputStream ips = socket.getInputStream();
                 OutputStream ops = socket.getOutputStream()) {

                PrintWriter pw = new PrintWriter(ops, true);
                pw.println(getRequest);
                pw.println("host: " + HOST_NAME);
                pw.println("");

                socket.shutdownOutput();

                Scanner scanner = new Scanner(ips);
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double balance(Account account) { /* GET /balance?account=5 HTTP/1.1 */
        getRequest = "GET /balance?account=" + account.getId() + " HTTP/1.1";
        client();
        return 0;
    }

    @Override
    public void deposit(Account account, double amount) { /* GET /deposit?account=5&amount=300 HTTP/1.1 */
        getRequest = "GET /deposit?account=" + account.getId() +
                "&amount=" + amount + " HTTP/1.1";
        client();
    }

    @Override
    public void withdraw(Account account, double amount) { /* GET /withdraw?account=5&amount=300 HTTP/1.1 */
        getRequest = "GET /withdraw?account=" + account.getId() +
                "&amount=" + amount + " HTTP/1.1";
        client();
    }

    @Override
    public void transfer(Account from, Account to, double amount) { /* GET /transfer?from=5&to=3&amount=300 HTTP/1.1 */
        getRequest = "GET /transfer?from=" + from.getId() +
                "&to=" + to.getId() + "&amount=" + amount + " HTTP/1.1";
        client();
    }


    public static void main(String[] args) {
        AtmClient atmClient = new AtmClient();
        Account account = new Account();
        account.setId("1");
        atmClient.balance(account);
        System.out.println();
        atmClient.deposit(account, 300);
        System.out.println();
        atmClient.withdraw(account, 150);
//        System.out.println();
//        Account accountTwo = new Account();
//        accountTwo.setId("2");
//        atmClient.transfer(account, accountTwo, 50);
    }
}
