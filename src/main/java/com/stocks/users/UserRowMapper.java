package com.stocks.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class UserRowMapper implements RowMapper<User> {

    @Override
    @Nullable
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getString("email"),
            rs.getString("firstName"),
            rs.getString("lastName")
            );
    }
    
}
