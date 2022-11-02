package com.stocks.stockservice.users;

import lombok.ToString;
import lombok.Setter;
import lombok.Getter;

@ToString
public class Users {
    @Getter @Setter private String email;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;

    public Users(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
