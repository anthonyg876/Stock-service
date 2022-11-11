package com.stocks.users;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    
    List<User> getUsers();
    int insertUser(User user);
    int deleteUser(String id);
    Optional<User> selectUserById(String id);
}
