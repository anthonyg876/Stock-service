package com.stocks.stockprices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StockPriceDataAccessService implements StockPriceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StockPriceDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StockPrice> getStockPrices(String id) {
        // String sql =  String.format("select dateOfPrice, low, open, volume, adjClosed, companyId from stockprices where companyId = %s", id);
        String sql = "select dateOfPrice, low, open, volume, adjClosed, companyId from stockPrices where companyid = ?";
        try {
            // Set fetch size higher when retrieving large amounts of data.
            jdbcTemplate.setFetchSize(10000);
            List<StockPrice> prices = jdbcTemplate.query(sql, new StockPriceRowMapper(), id);
            // List<StockPrice> prices = jdbcTemplate.query(sql2, new StockPriceRowMapper(), id);
            jdbcTemplate.setFetchSize(10);
            return prices;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertStockPrice(StockPrice stockPrice) {
        // @TODO: Check if the stockPrice exists.
        String sql = "insert into stockprices(dateOfPrice, low, open, volume, adjClosed, companyId) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
            sql,
            stockPrice.getDateOfPrice(), stockPrice.getLow(), stockPrice.getOpen(), stockPrice.getVolume(), stockPrice.getAdjClosed(), stockPrice.getCompanyId()
            );
    }

    @Override
    public int deleteStockPrice(String id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<StockPrice> selectStockPriceById(String id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
    @Override
    public void insertAllStockPrices(List<StockPrice> stockPrices) {
        String sql = "insert into stockprices(dateOfPrice, low, open, volume, adjClosed, companyId) values(?, ?, ?, ?, ?, ?)";
        
        ArrayList<Object[]> sqlArgs = new ArrayList<>();

        for (StockPrice stockPrice: stockPrices) {
            Object[] stockPriceData = {stockPrice.getDateOfPrice(), stockPrice.getLow(), stockPrice.getOpen(),
                                    stockPrice.getVolume(), stockPrice.getAdjClosed(), stockPrice.getCompanyId()}
                                    ;
            sqlArgs.add(stockPriceData);
        }

        jdbcTemplate.batchUpdate(sql, sqlArgs);
        
    }

}
