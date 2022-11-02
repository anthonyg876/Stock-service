package com.stocks.stock;

import java.util.List;
import java.util.Optional;

public interface StockDao {
    List<Stock> selectStocks();
    int insertStock(Stock stock);
    int deleteStock(String id);
    Optional<Stock> selectStockById(String id);
    // TODO: Update

}
