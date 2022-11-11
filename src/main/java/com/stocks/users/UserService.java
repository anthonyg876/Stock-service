package com.stocks.users;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    /**
     * Returns a user with a given email address.
     * The method checks to see if the database was able to retrieve said user.
     * If said user doesn't exist, it will return null. 
     * 
     * @param email
     * @return
     */
    public User getUser(String email) {
        Optional<User> user_ = userDao.selectUserById(email);

        if (!user_.isPresent()) {
            return null;
        }
        return user_.get();
    }

    /**
     * Adds user to the database.
     * Throws SQLException if the user already exists in the database.
     * @param user
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException, IllegalStateException {
        //Check if the user exists in the database.
        User exists;
        exists = getUser(user.getEmail());
        if (exists != null) {
            throw new SQLException();
        }
        
        int result = userDao.insertUser(user);
        if (result != 1) {
            throw new IllegalStateException("Error inserting index into database.");
        }
    }
    
    
}
