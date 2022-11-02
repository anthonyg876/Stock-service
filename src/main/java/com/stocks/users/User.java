package com.stocks.users;

import lombok.ToString;
import lombok.Setter;
import lombok.Getter;

@ToString
public class User {
    @Getter @Setter private String email;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
