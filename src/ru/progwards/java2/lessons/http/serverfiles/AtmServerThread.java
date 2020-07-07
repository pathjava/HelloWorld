// Oleg Kiselev
// 06.07.2020, 20:52

package ru.progwards.java2.lessons.http.serverfiles;

import ru.progwards.java2.lessons.http.model.Account;
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
    private Account account;
    private String methodName;
    private final List<AtmServerMethodParameters> methodParameter = new ArrayList<>();

    public AtmServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream ips = socket.getInputStream(); OutputStream ops = socket.getOutputStream()) {
            Scanner scanner = new Scanner(ips);
            getParameters(scanner);

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                PrintWriter pw = new PrintWriter(ops, true);
                pw.println(str); //TODO temp
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
        getAccount(); // TODO - temp for testing
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
        methodParameter.add(parameters);
    }

    private void getAccount() {
        StoreServiceImpl ssi = new StoreServiceImpl();
        account = ssi.get(methodParameter.get(0).getValue());
    }
}
