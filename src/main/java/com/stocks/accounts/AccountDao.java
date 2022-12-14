package com.stocks.accounts;

import java.util.List;
import java.util.Optional;

public interface AccountDao {
    List<Account> getAccounts();
    int insertAccount(Account account);
    int deleteAccount(int id);
    Optional<Account> getAccountById(int id);
    Optional<Account> getAccountByUsernameAndPassword(String username, String password);
}
