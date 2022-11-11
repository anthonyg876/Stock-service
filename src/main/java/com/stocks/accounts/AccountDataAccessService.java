package com.stocks.accounts;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertAccount(Account account) {
        String sql = "insert into Account(id, userName, password, held_by) values(?, ?, ?, ?)";
        return jdbcTemplate.update(
            sql, 
            account.getId(), account.getUserName(), account.getPassword(), account.getHeld_by()
            );
    }

    @Override
    public int deleteAccount(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<Account> selectAccountById(int id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    public Optional<Account> selectAcountByUsernameAndPassword(String username, String password) {
        String sql = "select id, username, password, held_by from account where username = ? and password = ?";
        try {
            return jdbcTemplate.query(sql, new AccountRowMapper(), username, password)
                    .stream().
                    findFirst();
        } catch (Exception e) {
            System.out.println("Could not find account with Credentials: " + username + ", password: " + password);
            return null;
        }
    }
    
}
