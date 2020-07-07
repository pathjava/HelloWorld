// Oleg Kiselev
// 06.07.2020, 20:52

package ru.progwards.java2.lessons.http;

import ru.progwards.java2.lessons.http.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.http.service.impl.StoreServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AtmServerThread implements Runnable {

    private final Socket socket;
    private String methodName;
    private final Map<String, String> methodParameter = new TreeMap<>();

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

    // "GET /balance?account=123&amount=678 HTTP/1.1"
    private void getParameters(Scanner scanner) { //TODO проверить корректность входящих данных - GET и HTTP
        String parameterString = getParameterString(scanner.nextLine());
        methodName = getMethodName(parameterString);
        getMethodParameters(parameterString);
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
        int index = str.indexOf("=");
        String param = str.substring(0, index);
        String value = str.substring(index + 1);
        methodParameter.put(param, value);
    }

    private void getResult() {
        StoreServiceImpl ssi = new StoreServiceImpl();
//        ssi.get(id);
    }


}
