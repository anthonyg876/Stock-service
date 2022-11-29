package com.stocks.stockprices;

import java.util.List;

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

    public double getAverageVolumeOfStock(String id, String initialDate, String afterDate) {
        return stockPriceDao.averageVolumeOfStock(id, initialDate, afterDate);
    }

}
