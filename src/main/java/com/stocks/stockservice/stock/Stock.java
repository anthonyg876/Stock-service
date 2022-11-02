package com.stocks.stockservice.stock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Stock {

    @Getter @Setter private String symbol;
    @Getter @Setter private String fullName;
    @Getter @Setter private String indexTicker;

    public Stock(String symbol, String fullName, String indexTicker) {
        this.symbol = symbol;
        this.fullName = fullName;
        this.indexTicker = indexTicker;
    }



}
