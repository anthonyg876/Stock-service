package com.stocks.accounts;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Account {
    
    @Getter @Setter private int id;
    @Getter @Setter private String userName;
    @Getter @Setter private String password;
    @Getter @Setter private String held_by;

    //For Jackson.
    public Account(){};

}
