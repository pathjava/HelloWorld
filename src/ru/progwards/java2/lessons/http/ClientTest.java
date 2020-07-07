// Oleg Kiselev
// 07.07.2020, 19:01

package ru.progwards.java2.lessons.http;

import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.http.service.impl.StoreServiceImpl;

import java.util.Date;

public class ClientTest {

    private void clientCreation(){
        StoreServiceImpl service = new StoreServiceImpl();
        Account account = new Account();
        account.setAmount(500);
        account.setDate(new Date());
        account.setHolder("Ivan");
        account.setId("5");
        account.setPin(15);
        service.insert(account);
        AccountServiceImpl asi = new AccountServiceImpl(service);
        asi.deposit(account,300);
    }

}
