package com.stocks.stockprices;

import java.util.List;
import java.util.Optional;

public interface StockPriceDao {

    public List<StockPrice> getStockPrices();
    int insertStockPrice(StockPrice stockPrice);
    int deleteStockPrice(String id);
    Optional<StockPrice> selectStockPriceById(String id);


}
