package com.stocks.accounts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    @Nullable
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Account(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("held_by")
                );
    }
    
}
