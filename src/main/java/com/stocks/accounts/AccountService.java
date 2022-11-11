package com.stocks.accounts;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> getAccounts() {
        return accountDao.getAccounts();
    }

    public void createAccount(Account account) {
        // @TODO: check in the index exists
        int result = accountDao.insertAccount(account);
        if (result != 1) {
            throw new IllegalStateException("Error inserting index into database.");
        }
    }

    public Account getAccount(int id) {
        Account account;
        try {
            Optional<Account> account_ = accountDao.selectAccountById(id);
            account = account_.get();
        } catch (Exception e) {
            e.printStackTrace();
            account = null;
        }
        return account;
    }

}
