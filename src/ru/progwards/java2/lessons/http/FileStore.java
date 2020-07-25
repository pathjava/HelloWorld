// Oleg Kiselev
// 25.07.2020, 15:09

package ru.progwards.java2.lessons.http;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import ru.progwards.java2.lessons.http.model.Account;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileStore {

    private static final String PATH_FILE = "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\http\\model\\accounts.json";
    private static final ObjectMapper mapper = new ObjectMapper(); /* создаем объект, отвечающий за сериализацию */
    private static final MapType type = mapper.getTypeFactory()
            .constructMapType(HashMap.class, String.class, Account.class); /* создаем тип/шаблон возвращаемого из Json объекта */
    private static HashMap<String, Account> accountsMap; /* хешмап с объектами/аккаунтами клиентов */
    private static final ReadWriteLock rwl = new ReentrantReadWriteLock();

    static {
        readerFromJson(); /* инициализируем хешмап accountsMap */
    }

    public static HashMap<String, Account> readStore() { /* возвращаем хешмап */
        rwl.readLock().lock();
        try {
            return accountsMap;
        } finally {
            rwl.readLock().unlock();
        }
    }

    public static void putOrUpdateAccount(String id, Account account) { /* кладем новый или обновляем аккаунт */
        rwl.writeLock().lock();
        try {
            accountsMap.put(id, account);
            writerToJson(); /* после того как обновили/добавли аккаунт, перезаписываем файл Json */
        } finally {
            rwl.writeLock().unlock();
        }
    }

    public static void delAccount(String id) { /* удаляем аккаунт */
        rwl.writeLock().lock();
        try {
            accountsMap.remove(id);
            writerToJson(); /* после того как удалили аккаунт, перезаписываем файл Json */
        } finally {
            rwl.writeLock().unlock();
        }
    }

    public static void readerFromJson() { /* читаем объекты из файла Json */
        try {
            accountsMap = mapper.readValue(Paths.get(PATH_FILE).toFile(), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writerToJson() { /* записываем объекты в файл Json */
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get(PATH_FILE).toFile(), accountsMap);
            readerFromJson(); /* после того как записали файл, получаем новые данные в accountsMap */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
