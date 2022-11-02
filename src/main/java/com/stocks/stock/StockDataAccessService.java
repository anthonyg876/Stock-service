package com.stocks.stock;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StockDataAccessService implements StockDao {

    @Override
    public List<Stock> selectStocks() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int insertStock(Stock stock) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int deleteStock(String id) {
        throw new UnsupportedOperationException("not implemented");
    }

    public Optional<Stock> selectStockById(String id) {
    throw new UnsupportedOperationException("not implemented");
    }
}
