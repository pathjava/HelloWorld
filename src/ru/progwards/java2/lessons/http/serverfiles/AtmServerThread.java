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
    private final List<String> methodParamValue = new ArrayList<>();

    public AtmServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream ips = socket.getInputStream(); OutputStream ops = socket.getOutputStream()) {
            Scanner scanner = new Scanner(ips);

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.contains("GET")) {
                    getParameters(str);
                    accountOperations();

                    PrintWriter pw = new PrintWriter(ops, true);
                    pw.println("HTTP/1.1 200 OK");
                    pw.println("Content-Type: text/html; charset=utf-8");
                    pw.println("Content-Length: " + answer.length());
                    pw.println("");
                    pw.println(answer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // "GET /balance?account=12 HTTP/1.1"
    // "GET /deposit?account=5&amount=300 HTTP/1.1"
    // "GET /withdraw?account=12&amount=6.78 HTTP/1.1"
    // "GET /transfer?account=12&account=15&amount=6.78 HTTP/1.1"
    private void getParameters(String scannerStr) {
        String parameterString = getParameterString(scannerStr);
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
        int index = str.indexOf("=");
        methodParamValue.add(str.substring(index + 1));
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
        Account account = service.get(methodParamValue.get(0));
        double amount = asi.balance(account);
        answer = "Баланс аккаунта id" + account.getId() + " составляет " + amount;
    }

    private void operationDeposit() {
        Account account = service.get(methodParamValue.get(0));
        double sum = Double.parseDouble(methodParamValue.get(1));
        asi.deposit(account, sum);
        answer = "Баланс аккаунта id" + account.getId() +
                " пополнен на сумму " + sum + " и составляет " + account.getAmount();
    }

    private void operationWithdraw() {
        Account account = service.get(methodParamValue.get(0));
        double sum = Double.parseDouble(methodParamValue.get(1));
        asi.withdraw(account, sum);
        answer = "С аккаунта id" + account.getId() +
                " списана сумма " + sum + ", остаток на счёте " + account.getAmount();
    }

    private void operationTransfer() {
        Account accountFrom = service.get(methodParamValue.get(0));
        Account accountTo = service.get(methodParamValue.get(1));
        double sum = Double.parseDouble(methodParamValue.get(2));
        asi.transfer(accountFrom, accountTo, sum);
        answer = "С аккаунта id" + accountFrom.getId() +
                " переведена сумма " + sum + " на аккаунт id" + accountTo.getId() + "\n" +
                "Баланс аккаунта id" + accountFrom.getId() + " составляет " + accountFrom.getAmount() + "\n" +
                "Баланс аккаунта id" + accountTo.getId() + " составляет " + accountTo.getAmount();
    }
}
