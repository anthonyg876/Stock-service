package com.stocks.users;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.getUser(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not get user with email: " + email);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping(path = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        
        try {
        userService.addUser(user);
        } catch (SQLException e) {
            System.out.println("A user with email: " + user.getEmail() + " already exists.");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user already exists.");    
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add user with email into the database.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to add user.");
        }
            return ResponseEntity.status(HttpStatus.OK).body("Successfully added user.");
    }
    
}
