// Oleg Kiselev
// 06.07.2020, 20:52

package ru.progwards.java2.lessons.http.serverfiles;

import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.http.service.impl.FileStoreServiceImpl;
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
//    private final StoreServiceImpl service = new StoreServiceImpl(); /* аккаунты из памяти */
    private final FileStoreServiceImpl service = new FileStoreServiceImpl(); /* аккаунты из файла Json */
    private final AccountServiceImpl asi = new AccountServiceImpl(service);
    private final List<String> methodParamValue = new ArrayList<>();

    public AtmServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream ips = socket.getInputStream(); OutputStream ops = socket.getOutputStream()) {
            Scanner scanner = new Scanner(ips); /* передаем полученные данные из InputStream в сканер */

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine(); /* получаем строку из сканера */
                if (str.contains("GET")) { /* если строка содержит GET */
                    getParameters(str); /* запускам обработку строки */
                    accountOperations(); /* выполняем операции с балансом аккаунта */

                    PrintWriter pw = new PrintWriter(ops, true); /* возвращаем результат клиенту */
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
    private void getParameters(String scannerStr) { /* парсим строку, полученную из сканера */
        String parameterString = getParameterString(scannerStr);
        getMethodName(parameterString);
        getMethodParameters(parameterString);
    }

    private String getParameterString(String str) { /* получаем часть строки с именем метода и параметрами */
        int indexStart = str.indexOf("/");
        int indexEnd = str.lastIndexOf(" H");
        return str.substring(indexStart + 1, indexEnd);
    }

    private void getMethodName(String str) { /* получаем имя метода из строки */
        int indexEnd = str.indexOf("?");
        methodName = str.substring(0, indexEnd);
    }

    private void getMethodParameters(String str) { /* получаем значения методов из строки */
        int indexStart = str.indexOf("?");
        String strParam = str.substring(indexStart + 1);
        if (strParam.contains("&")) { /* если значений несколько */
            String[] arrParam = strParam.split("&");
            for (String s : arrParam)
                addMethodParameter(s);
        } else /* если значение одно (так как одним может быть только аккаунт, то данное действие необязательное) */
            addMethodParameter(strParam);
    }

    private void addMethodParameter(String str) { /* получаем значения параметров метода */
        int index = str.indexOf("=");
        methodParamValue.add(str.substring(index + 1));
    }

    private void accountOperations() { /* в зависимости от имени метода вызываем соответствующий метод */
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

    private void operationBalance() { /* получаем баланс аккаунта */
        Account account = service.get(methodParamValue.get(0)); /* получаем аккаунт по id */
        double amount = asi.balance(account); /* запрашиваем баланс */
        answer = "Баланс аккаунта id" + account.getId() + " составляет " + amount; /* формируем тело ответа клиенту */
    }

    private void operationDeposit() { /* пополняем счёт */
        Account account = service.get(methodParamValue.get(0));
        double sum = Double.parseDouble(methodParamValue.get(1)); /* сумма пополнения */
        asi.deposit(account, sum);
        answer = "Баланс аккаунта id" + account.getId() +
                " пополнен на сумму " + sum + " и составляет " + account.getAmount();
    }

    private void operationWithdraw() { /* списываем со счета */
        Account account = service.get(methodParamValue.get(0));
        double sum = Double.parseDouble(methodParamValue.get(1)); /* сумма списания */
        asi.withdraw(account, sum);
        answer = "С аккаунта id" + account.getId() +
                " списана сумма " + sum + ", остаток на счёте " + account.getAmount();
    }

    private void operationTransfer() { /* переводим с одного счета на другой */
        Account accountFrom = service.get(methodParamValue.get(0)); /* от кого переводим */
        Account accountTo = service.get(methodParamValue.get(1)); /* кому переводим */
        double sum = Double.parseDouble(methodParamValue.get(2)); /* сумма перевода */
        asi.transfer(accountFrom, accountTo, sum);
        answer = "С аккаунта id" + accountFrom.getId() +
                " переведена сумма " + sum + " на аккаунт id" + accountTo.getId() + "\n" +
                "Баланс аккаунта id" + accountFrom.getId() + " составляет " + accountFrom.getAmount() + "\n" +
                "Баланс аккаунта id" + accountTo.getId() + " составляет " + accountTo.getAmount();
    }
}
