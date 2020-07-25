// Oleg Kiselev
// 25.07.2020, 15:09

package ru.progwards.java2.lessons.http;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;
import ru.progwards.java2.lessons.http.model.Account;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileStore {

    private static final String PATH_FILE = "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\http\\model\\accounts.json";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final MapType type = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Account.class);
    private static HashMap<String, Account> accountsMap;
    private static final ReadWriteLock rwl = new ReentrantReadWriteLock();

    //    {
//
//    }
    private static void reader() {
        try {
            accountsMap = mapper.readValue(Paths.get(PATH_FILE).toFile(), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writer(){

    }

    public static HashMap<String, Account> readStore() {
        rwl.readLock().lock();
        reader();
        try {
            return accountsMap;
        } finally {
            rwl.readLock().unlock();
        }
    }

    public static HashMap<String, Account> writeStore() {
        rwl.writeLock().lock();
        try {
            return accountsMap;
        } finally {
            rwl.writeLock().unlock();
        }
    }

}
