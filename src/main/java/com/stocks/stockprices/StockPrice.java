package com.stocks.stockprices;

import lombok.*;
import java.sql.Date;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StockPrice {
    
    @Getter @Setter private Date dateOfPrice;
    @Getter @Setter private double low;
    @Getter @Setter private double open;
    @Getter @Setter private int volume;
    @Getter @Setter private double adjClosed;
    @Getter @Setter private String companyId;

    public StockPrice() {}

}
