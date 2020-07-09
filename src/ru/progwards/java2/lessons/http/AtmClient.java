// Oleg Kiselev
// 06.07.2020, 19:03

package ru.progwards.java2.lessons.http;

import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.AccountService;
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
    private static final String GET = "GET /";
    private static final String HTTP_1_1 = " HTTP/1.1";
    private String getRequest;

    private void client() {
        try (Socket socket = new Socket(HOST_NAME, PORT_ID)) {
            try (InputStream ips = socket.getInputStream(); OutputStream ops = socket.getOutputStream()) {

                PrintWriter pw = new PrintWriter(ops, true);
                pw.println(getRequest); /* отправляем сформированную строку с GET запросом */
                pw.println("host: " + HOST_NAME);
                pw.println("");

                socket.shutdownOutput(); /* полузакрытие сокета */

                Scanner scanner = new Scanner(ips);
                while (scanner.hasNextLine()) { /* выводим на консоль ответ от сервера */
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double balance(Account account) { /* GET /balance?account=5 HTTP/1.1 */
        checkingAccountNull(account);
        getRequest = GET + "balance?account=" + account.getId() + HTTP_1_1; /* формируем строку с GET запросом */
        client(); /* запускаем метод для взаимодействия с сервером */
        return 0;
    }

    @Override
    public void deposit(Account account, double amount) { /* GET /deposit?account=5&amount=300 HTTP/1.1 */
        checkingAccountNull(account);
        checkingAmount(amount);
        getRequest = GET + "deposit?account=" + account.getId() +
                "&amount=" + amount + HTTP_1_1;
        client();
    }

    @Override
    public void withdraw(Account account, double amount) { /* GET /withdraw?account=5&amount=300 HTTP/1.1 */
        checkingAccountNull(account);
        checkingAmount(amount);
        getRequest = GET + "withdraw?account=" + account.getId() +
                "&amount=" + amount + HTTP_1_1;
        client();
    }

    @Override
    public void transfer(Account from, Account to, double amount) { /* GET /transfer?from=5&to=3&amount=300 HTTP/1.1 */
        checkingAccountNull(from, to);
        checkingAmount(amount);
        getRequest = GET + "transfer?from=" + from.getId() +
                "&to=" + to.getId() + "&amount=" + amount + HTTP_1_1;
        client();
    }

    private void checkingAccountNull(Account... account) {
        if (account == null)
            throw new IllegalArgumentException("Account не может быть null!");
    }

    private void checkingAmount(double amount) {
        if (amount > Double.MAX_VALUE)
            throw new IllegalArgumentException("Значение amount (" + amount + ") больше допустимого значения!");
        if (amount < 0.0)
            throw new IllegalArgumentException("Значение amount (" + amount + ") не может быть меньше 0.0!");
    }


    public static void main(String[] args) {
        AtmClient atmClient = new AtmClient();

        for (int i = 0; i < 10; i++) { /* выполняем операции с балансом аккаунтов */
            Account account = new StoreServiceImpl().get("" + i);
            atmClient.balance(account);
            System.out.println();
            atmClient.deposit(account, 300);
            System.out.println();
            atmClient.withdraw(account, 150);
            System.out.println();
        }
        Account from = new StoreServiceImpl().get("3");
        Account to = new StoreServiceImpl().get("5");
        atmClient.transfer(from, to, 100);
        System.out.println();
    }
}
