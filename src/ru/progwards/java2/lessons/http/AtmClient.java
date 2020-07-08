// Oleg Kiselev
// 06.07.2020, 19:03

package ru.progwards.java2.lessons.http;

import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.AccountService;
import ru.progwards.java2.lessons.http.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.http.service.impl.StoreServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AtmClient implements AccountService {

    private static final int PORT_ID = 45000;
    private static final String HOST_NAME = "localhost";

    private void client() {
        try (Socket socket = new Socket(HOST_NAME, PORT_ID)) {
            String request = ""; //TODO temp

            InputStream ips = socket.getInputStream();
            OutputStream ops = socket.getOutputStream();

            PrintWriter pw = new PrintWriter(ops, true);
            pw.println("GET /balance?account=5 HTTP/1.1");
//            pw.println("GET /deposit?account=5&amount=300 HTTP/1.1");
//            pw.println("GET /withdraw?account=5&amount=300 HTTP/1.1");
            pw.println("host: localhost");
            pw.println("");

            Scanner scanner = new Scanner(ips);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public double balance(Account account) {
        return 0;
    }

    @Override
    public void deposit(Account account, double amount) {

    }

    @Override
    public void withdraw(Account account, double amount) {

    }

    @Override
    public void transfer(Account from, Account to, double amount) {

    }

    private void clientCreation() { //TODO - for testing
        StoreServiceImpl service = new StoreServiceImpl();
        Account account = new Account();
        service.insert(account);
        AccountServiceImpl asi = new AccountServiceImpl(service);
        asi.deposit(account, 300);
    }


    public static void main(String[] args) {
        AtmClient atmClient = new AtmClient();
        atmClient.clientCreation(); //TODO - for testing
        atmClient.client();
    }
}
