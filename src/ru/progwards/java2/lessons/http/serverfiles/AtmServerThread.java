// Oleg Kiselev
// 06.07.2020, 20:52

package ru.progwards.java2.lessons.http.serverfiles;

import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.http.service.impl.StoreServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtmServerThread implements Runnable {

    private final Socket socket;
    private String methodName;
    private String answer;
    private final StoreServiceImpl service = new StoreServiceImpl();
    private final AccountServiceImpl asi = new AccountServiceImpl(service);
    private final List<AtmServerMethodParameters> methodParam = new ArrayList<>();

    public AtmServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream ips = socket.getInputStream(); OutputStream ops = socket.getOutputStream()) {
            Scanner scanner = new Scanner(ips);
            getParameters(scanner);
            accountOperations();

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                PrintWriter pw = new PrintWriter(ops, true);
                pw.println("HTTP/1.1 200 OK");
                pw.println("Content-Type: text/html; charset=utf-8");
                pw.println("Content-Length: " + answer.length());
                pw.println("");
                pw.println(answer);

                if (str.equalsIgnoreCase("quit"))
                    break;

                socket.shutdownOutput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // "GET /balance?account=12 HTTP/1.1"
    // "GET /deposit?account=5&amount=300 HTTP/1.1"
    // "GET /withdraw?account=12&amount=6.78 HTTP/1.1"
    // "GET /transfer?account=12&account=15&amount=6.78 HTTP/1.1"
    private void getParameters(Scanner scanner) {
        String parameterString = getParameterString(scanner.nextLine());
        getMethodName(parameterString);
        getMethodParameters(parameterString);
    }

    private String getParameterString(String str) {
        int indexStart = str.indexOf("/");
        int indexEnd = str.lastIndexOf(" H");
        return str.substring(indexStart + 1, indexEnd);
    }

    private void getMethodName(String str) {
        int indexEnd = str.indexOf("?");
        methodName = str.substring(0, indexEnd);
    }

    private void getMethodParameters(String str) {
        int indexStart = str.indexOf("?");
        String strParam = str.substring(indexStart + 1);
        if (strParam.contains("&")) {
            String[] arrParam = strParam.split("&");
            for (String s : arrParam)
                addMethodParameter(s);
        } else
            addMethodParameter(strParam);
    }

    private void addMethodParameter(String str) {
        AtmServerMethodParameters parameters = new AtmServerMethodParameters();
        int index = str.indexOf("=");
        parameters.setParam(str.substring(0, index));
        parameters.setValue(str.substring(index + 1));
        methodParam.add(parameters);
    }

    private void accountOperations() {
        switch (methodName) {
            case "balance":
                operationBalance();
                break;
            case "deposit":
                operationDeposit();
                break;
            case "withdraw":
                operationWithdraw();
                break;
            case "transfer":
                operationTransfer();
                break;
        }
    }

    private void operationBalance() {
        Account account = getAccount(methodParam.get(0).getValue());
        double amount = asi.balance(account);
        answer = "Баланс аккаунта id" + account.getId() + " составляет " + amount;
    }

    private void operationDeposit() {
        Account account = getAccount(methodParam.get(0).getValue());
        double sum = Double.parseDouble(methodParam.get(1).getValue());
        service.insert(account);
        asi.deposit(account, sum);
        answer = "Баланс аккаунта id" + account.getId() +
                " пополнен на сумму " + sum + " и составляет " + account.getAmount();
    }

    private void operationWithdraw() {
        Account account = getAccount(methodParam.get(0).getValue());
        double sum = Double.parseDouble(methodParam.get(1).getValue());
        service.insert(account);
        asi.withdraw(account, sum);
        answer = "С аккаунта id" + account.getId() +
                " списана сумма " + sum + ", остаток на счёте " + account.getAmount();
    }

    private void operationTransfer() {
        Account accountOne = getAccount(methodParam.get(0).getValue());
        Account accountTwo = getAccount(methodParam.get(1).getValue());
        double sum = Double.parseDouble(methodParam.get(2).getValue());
        service.insert(accountOne);
        service.insert(accountTwo);
        asi.transfer(accountOne, accountTwo, sum);
        answer = "С аккаунта id" + accountOne.getId() +
                " переведена сумма " + sum + " на аккаунт id" + accountTwo.getId() + "\n" +
                "Баланс аккаунта id" + accountOne.getId() + " составляет " + accountOne.getAmount() + "\n" +
                "Баланс аккаунта id" + accountTwo.getId() + " составляет " + accountTwo.getAmount();
    }

    private Account getAccount(String id) {
        StoreServiceImpl ssi = new StoreServiceImpl();
        return ssi.get(id);
    }
}
