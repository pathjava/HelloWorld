// Oleg Kiselev
// 06.07.2020, 19:03

package ru.progwards.java2.lessons.http;

import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.AccountService;

public class AtmClient implements AccountService {
    @Override
    public double balance(Account account) {
        return 0;
    }

    @Override
    public void deposit(Account account, double amount) {

    }

    @Override
    public void withdraw(Account account, double amount) {

    }

    @Override
    public void transfer(Account from, Account to, double amount) {

    }
}
