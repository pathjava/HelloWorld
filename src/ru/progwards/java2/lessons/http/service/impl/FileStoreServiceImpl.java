// Oleg Kiselev
// 24.07.2020, 17:54

package ru.progwards.java2.lessons.http.service.impl;

import ru.progwards.java2.lessons.http.FileStore;
import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.StoreService;

import java.util.Collection;

public class FileStoreServiceImpl implements StoreService {

    @Override
    public Account get(String id) {
        Account account = FileStore.readStore().get(id);
        if (account == null)
            throw new RuntimeException("Account not found by id:" + id);
        return account;
    }

    @Override
    public Collection<Account> get() {
        if (FileStore.readStore().size() == 0)
            throw new RuntimeException("FileStore is empty");
        return FileStore.readStore().values();
    }

    @Override
    public void delete(String id) {
        if (FileStore.readStore().get(id) == null)
            throw new RuntimeException("Account not found by id:" + id);
        FileStore.writeStore().remove(id);
    }

    @Override
    public void insert(Account account) {
        FileStore.writeStore().put(account.getId(), account);
    }

    @Override
    public void update(Account account) {
        if (FileStore.readStore().get(account.getId()) == null)
            throw new RuntimeException("Account not found by id:" + account.getId());
        this.insert(account);
    }


    /* for testing */
    public static void main(String[] args) {
        FileStoreServiceImpl fss = new FileStoreServiceImpl();
        fss.get("3");
    }
}
