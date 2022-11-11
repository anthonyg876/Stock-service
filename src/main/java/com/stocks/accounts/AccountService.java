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
    /**
     * Create new account in the database.
     * The account parameter is the account object. Only give it a username, 
     * password, and email of your user account.
     * 
     * @param account
     */
    public void createAccount(Account account) throws IllegalStateException {
        int result = accountDao.insertAccount(account);
        if (result != 1) {
            throw new IllegalStateException("Error inserting index into database.");
        }
    }

    public Account verifyAccount(String username, String password) {
        return accountDao.getAccountByUsernameAndPassword(username, password).get();
        
    }

    public Account getAccountById(int id) {
        Account account;
        try {
            Optional<Account> account_ = accountDao.getAccountById(id);
            account = account_.get();
        } catch (Exception e) {
            e.printStackTrace();
            account = null;
        }
        return account;
    }

    public void deleteAccount(int id) throws IllegalStateException, Exception {
        Account account = accountDao.getAccountById(id).get();
        if (account == null) {
            throw new Exception("The account with said Id does not exist");
        }

        if (accountDao.deleteAccount(id) != 1) {
            throw new IllegalStateException("Could not delete accout with given id.");
        };
    }

}
