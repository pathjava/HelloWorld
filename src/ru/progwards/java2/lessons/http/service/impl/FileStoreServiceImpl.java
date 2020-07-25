// Oleg Kiselev
// 24.07.2020, 17:54

package ru.progwards.java2.lessons.http.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;
import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.StoreService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;

public class FileStoreServiceImpl implements StoreService {

    private static final String PATH_FILE = "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\http\\model\\accounts.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final MapType type = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Account.class);
    private HashMap<String, Account> accountsMap;

    {
        try {
            accountsMap = mapper.readValue(Paths.get(PATH_FILE).toFile(), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account get(String id) {
        Account account = null;
        if (accountsMap != null)
            account = accountsMap.get(id);
        if (account == null)
            throw new RuntimeException("Account not found by id:" + id);
        return account;
    }

    @Override
    public Collection<Account> get() {
        if (accountsMap.size() == 0)
            throw new RuntimeException("FileStore is empty");
        return accountsMap.values();
    }

    @Override
    public void delete(String id) {
        if (accountsMap.get(id) == null)
            throw new RuntimeException("Account not found by id:" + id);
        accountsMap.remove(id);
    }

    @Override
    public void insert(Account account) {
        accountsMap.put(account.getId(), account);
    }

    @Override
    public void update(Account account) {
        if (accountsMap.get(account.getId()) == null)
            throw new RuntimeException("Account not found by id:" + account.getId());
        this.insert(account);
    }


    /* for testing */
    public static void main(String[] args) {
        FileStoreServiceImpl fss = new FileStoreServiceImpl();
        fss.get("3");
    }
}
