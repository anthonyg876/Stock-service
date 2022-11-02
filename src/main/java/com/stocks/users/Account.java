package com.stocks.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Account {
    
    @Getter @Setter private int id;
    @Getter @Setter private String userName;
    @Getter @Setter private String password;
    @Getter @Setter private String held_by;

    public Account(int id, String userName, String password, String held_by) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.held_by = held_by;
    }

}
