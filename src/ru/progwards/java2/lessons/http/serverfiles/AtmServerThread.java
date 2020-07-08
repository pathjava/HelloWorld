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
                pw.println(answer); //TODO temp
                //TODO - think about - shutdownOutput();

                if (str.equalsIgnoreCase("quit"))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // "GET /balance?account=12 HTTP/1.1"
    // "GET /deposit?account=5&amount=300 HTTP/1.1"
    // "GET /withdraw?account=12&amount=6.78 HTTP/1.1"
    // "GET /transfer?account=12&account=15&amount=6.78 HTTP/1.1"
    private void getParameters(Scanner scanner) { //TODO проверить корректность входящих данных - GET и HTTP
        String parameterString = getParameterString(scanner.nextLine());
        methodName = getMethodName(parameterString);
        getMethodParameters(parameterString);
//        getAccount(); // TODO - temp for testing
    }

    private String getParameterString(String str) {
        int indexStart = str.indexOf("/");
        int indexEnd = str.lastIndexOf(" H");
        return str.substring(indexStart + 1, indexEnd);
    }

    private String getMethodName(String str) {
        int indexEnd = str.indexOf("?");
        return str.substring(0, indexEnd);
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
        AccountServiceImpl asi = new AccountServiceImpl();
        StoreServiceImpl service = new StoreServiceImpl();
        Account accountOne;
        Account accountTwo;
        switch (methodName) {
            case "balance":
                accountOne = getAccount(methodParam.get(0).getValue());
                double amount = asi.balance(accountOne);
                answer = "Баланс аккаунта " + accountOne.getId() + " составляет " + amount;
                break;
            case "deposit":
                accountOne = getAccount(methodParam.get(0).getValue());
                double sum = Double.parseDouble(methodParam.get(1).getValue());
                service.insert(accountOne);
                asi.deposit(accountOne, sum);
                answer = "Баланс аккаунта " + accountOne.getId() +
                        " пополнен на сумму " + sum + " и составляет " + accountOne.getAmount();
                break;
            case "withdraw":
                accountOne = getAccount(methodParam.get(0).getValue());
                sum = Double.parseDouble(methodParam.get(1).getValue());
                asi.withdraw(accountOne, sum);
                answer = "С аккаунта " + accountOne.getId() +
                        " списана сумма " + sum + ", остаток на счёте " + accountOne.getAmount();
                break;
            case "transfer":
                accountOne = getAccount(methodParam.get(0).getValue());
                accountTwo = getAccount(methodParam.get(1).getValue());
                sum = Double.parseDouble(methodParam.get(2).getValue());
                asi.transfer(accountOne, accountTwo, sum);
                answer = "С аккаунта " + accountOne.getId() +
                        " переведена сумма " + sum + " на аккаунт " + accountTwo.getId() + "\n" +
                        "Баланс аккаунта " + accountOne.getId() + " составляет " + accountOne.getAmount() + "\n" +
                        "Баланс аккаунта " + accountTwo.getId() + " составляет " + accountTwo.getAmount();
                break;
        }
    }

    private Account getAccount(String id) {
        StoreServiceImpl ssi = new StoreServiceImpl();
        return ssi.get(id);
    }
}
