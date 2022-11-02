package com.stocks.stockservice.stock;

import lombok.*;
import java.sql.Date;

@ToString
@AllArgsConstructor
public class StockPrices {
    
    @Getter @Setter private Date dateOfPrice;
    @Getter @Setter private double low;
    @Getter @Setter private double open;
    @Getter @Setter private int volume;
    @Getter @Setter private double adjClosed;
    @Getter @Setter private String companyId;

}
