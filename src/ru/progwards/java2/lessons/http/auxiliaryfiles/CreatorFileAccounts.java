// Oleg Kiselev
// 24.07.2020, 18:05

package ru.progwards.java2.lessons.http.auxiliaryfiles;

import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import ru.progwards.java2.lessons.http.model.Account;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreatorFileAccounts {

    private static final String PATH_FILE = "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\http\\model\\accounts.json";

    void creator() throws IOException {
        Map<String, Account> accountMap = new HashMap<>();
        Account account;
        for (int i = 1; i <= 10; i++) {
            account = new Account();
            account.setDate(new Date());
            account.setHolder("Account_" + i);
            account.setPin(i);
            account.setId("" + i);
            account.setAmount(150 * i);
            accountMap.put(account.getId(), account);
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get(PATH_FILE).toFile(), accountMap);

        for (Map.Entry<String, Account> entry : accountMap.entrySet()) {
            String jsonString = mapper.writeValueAsString(entry.getValue());
            System.out.println(jsonString);
        }
    }

    public static void main(String[] args) {
        CreatorFileAccounts cfa = new CreatorFileAccounts();
        try {
            cfa.creator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
