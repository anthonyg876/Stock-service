package com.stocks.stockprices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StockPriceDao {
    // Crud operations
    public List<StockPrice> getStockPrices(String id);
    int insertStockPrice(StockPrice stockPrice);
    void insertAllStockPrices(List<StockPrice> stockPrices);
    int deleteStockPrice(String id);
    Optional<StockPrice> selectStockPriceById(String id);
    int getTotalTuples();
    //Queries for final project
    double averageVolumeOfStock(String id, String begin, String end);

    double averageClosingPrice(String id, String begin, String end);

    List<Map<String, Object>> getStockPriceUpdates(String id, String begin, String end);

    List<Map<String, Object>> getPricePercentageChanges(String id, String begin, String end);

    List<StockPriceResult> getHighestGrowingStocks(String begin, String end, String index);

    List<StockPriceResult> lowestGrownStocksInMarket(String begin, String end);

}
