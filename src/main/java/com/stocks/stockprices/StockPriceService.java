package com.stocks.stockprices;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class StockPriceService {

    private final StockPriceDao stockPriceDao;

    public StockPriceService(StockPriceDao stockPriceDao) {
        this.stockPriceDao = stockPriceDao;
    }
    
    /**
     * Returns the stockPrices for all stocks with the given id.
     * The id param is the symbol of the wanted stock.
     * Returns all the stockPrices of a given stock.
     * 
     * @param id
     * @return List
     */
    public List<StockPrice> getStockPrices(String id) {
        return stockPriceDao.getStockPrices(id);
    }

    public void addStockPrices(List<StockPrice> stockPrices) {
        stockPriceDao.insertAllStockPrices(stockPrices);
    }

    public void addStockPrice(StockPrice stockPrice) {
        int result = stockPriceDao.insertStockPrice(stockPrice);

        if (result != 1) {
            throw new IllegalStateException("Error inserting index into database.");
        }
    }

    public int getTotalTuples() {
        return stockPriceDao.getTotalTuples();
    }
    public double getAverageVolumeOfStock(String id, String initialDate, String afterDate) {
        return stockPriceDao.averageVolumeOfStock(id, initialDate, afterDate);
    }

    public double getAverageClosingPrice(String id, String initialDate, String endDate) {
        return stockPriceDao.averageClosingPrice(id, initialDate, endDate);
    }

    public List<Map<String, Object>> getPriceChanges(String id, String initialDate, String endDate) {
        return stockPriceDao.getStockPriceUpdates(id, initialDate, endDate);
    }

    public List<Map<String, Object>> getPercentageChanges(String id, String initialDate, String endDate) {
        return stockPriceDao.getPricePercentageChanges(id, initialDate, endDate);
    }

    public List<StockPriceResult> getHighestGrowingStocks(String initialDate, String endDate, String index) {
        return stockPriceDao.getHighestGrowingStocks(initialDate, endDate, index);
    }

    public List<StockPriceResult> getLowestGrowingStocks(String initalDate, String endDate) {
        return stockPriceDao.lowestGrownStocksInMarket(initalDate, endDate);
    }
}
