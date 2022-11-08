package com.stocks.stock;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Stock {

    @Getter @Setter private String symbol;
    @Getter @Setter private String fullName;
    @Getter @Setter private String inDJGT;
    @Getter @Setter private String inDJI;
    @Getter @Setter private String inNDX;
    
    public Stock() {}
}
