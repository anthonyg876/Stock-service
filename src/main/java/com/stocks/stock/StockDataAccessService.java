package com.stocks.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StockDataAccessService implements StockDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StockDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stock> selectStocks() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int insertStock(Stock stock) {
        String sql = "insert into stock(symbol, fullName, inDJGT, inDJI, inNDX) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
            sql,
            stock.getSymbol(), stock.getFullName(), stock.getInDJGT(), stock.getInDJI(), stock.getInNDX()
            );
    }

    @Override
    public int deleteStock(String id) {
        throw new UnsupportedOperationException("not implemented");
    }

    public Optional<Stock> selectStockById(String id) {
    throw new UnsupportedOperationException("not implemented");
    }
}
