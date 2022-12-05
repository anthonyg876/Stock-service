package com.stocks.stock;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StockDataAccessService implements StockDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StockDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stock> selectStocks() {
        String sql = "select symbol, fullName, inDJGT, inDJI, inNDX from agravier.stock";
        jdbcTemplate.setFetchSize(2000);
        List<Stock> stocks = jdbcTemplate.query(sql, new StockRowMapper());
        jdbcTemplate.setFetchSize(10);
        return stocks;
    }

    @Override
    public List<String> getStockIds() {
        String sql = "select symbol from agravier.stock";
        jdbcTemplate.setFetchSize(2500);
        List<String> symbols = jdbcTemplate.queryForList(sql, String.class);
        jdbcTemplate.setFetchSize(10);
        return symbols;
    }

    @Override
    public List<String> getStockNames() {
        String sql = "select fullName from agravier.stock";
        jdbcTemplate.setFetchSize(2500);
        List<String> names = jdbcTemplate.queryForList(sql, String.class);
        jdbcTemplate.setFetchSize(10);
        return names;
    }

    @Override
    public int insertStock(Stock stock) {
        String sql = "insert into agravier.stock(symbol, fullName, inDJGT, inDJI, inNDX) values(?, ?, ?, ?, ?)";
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
