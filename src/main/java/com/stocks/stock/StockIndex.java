package com.stocks.stock;

import lombok.*;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StockIndex {
    
    @Getter @Setter private String indexTicker;
    @Getter @Setter private int numberOfStocks;
    @Getter @Setter private String name;
    
}
