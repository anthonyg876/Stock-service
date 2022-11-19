package com.stocks.stockprices;

import java.sql.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StockPrice {
    
    @Getter @Setter private Date dateOfPrice;
    @Getter @Setter private double open;
    @Getter @Setter private double high;
    @Getter @Setter private double low;
    @Getter @Setter private double adjClosed;
    @Getter @Setter private int volume;
    @Getter @Setter private String companyId;
    
    public StockPrice() {}

}
