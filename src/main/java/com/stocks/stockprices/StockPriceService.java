package com.stocks.stockprices;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StockPriceService {

    private final StockPriceDao stockPriceDao;

    public StockPriceService(StockPriceDao stockPriceDao) {
        this.stockPriceDao = stockPriceDao;
    }
    
    public List<StockPrice> getStockPrices() {
        return stockPriceDao.getStockPrices();
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

}
