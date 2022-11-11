package com.stocks.users;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
    @Getter @Setter private String email;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;

    public User() {};
}
