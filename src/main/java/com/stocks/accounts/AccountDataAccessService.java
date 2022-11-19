package com.stocks.accounts;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDataAccessService implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AccountDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> getAccounts() {
        String sql = "select id, username, password, held_by from account fetch first 100 rows only";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    @Override
    public int insertAccount(Account account) {
        String sql = "insert into Account(userName, password, held_by) values(?, ?, ?)";
        return jdbcTemplate.update(
            sql, 
            account.getUserName(), account.getPassword(), account.getHeld_by()
            );
    }

    @Override
    public int deleteAccount(int id) {
        String sql = "delete from account where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Account> getAccountById(int id) {
        String sql = "select id, username, password, held_by from account where id = ?";
        return jdbcTemplate.query(sql, new AccountRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<Account> getAccountByUsernameAndPassword(String username, String password) throws NoSuchElementException {
        String sql = "select id, username, password, held_by from account where username = ? and password = ?";
        try {
            return jdbcTemplate.query(sql, new AccountRowMapper(), username, password)
                    .stream().
                    findFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Could not find account with Credentials: " + username + ", password: " + password);
            return null;
        } catch (DataAccessException e) {
            System.out.println("Error accessing the data with the given query.");
            return null;
        }
    }
    
}
