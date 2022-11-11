package com.stocks.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataAccessService implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into Users(email, firstName, lastName) values(?, ?, ?)";
        return jdbcTemplate.update(
            sql,
            user.getEmail(), user.getFirstName(), user.getLastName()
            );
    }

    @Override
    public int deleteUser(String id) {
        String sql = "delete from Users where email = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<User> selectUserById(String id) {
        String sql = "select email, firstName, lastName from users where email = ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), id).stream().findFirst();
    }
    
}
