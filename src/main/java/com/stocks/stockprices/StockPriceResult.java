package com.stocks.stockprices;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockPriceResult {

    private String nameOfStock;
    private double percentIncrease;

    public StockPriceResult() {};
    
}
