package com.stocks.stockprices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class StockPriceDataAccessService implements StockPriceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StockPriceDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StockPrice> getStockPrices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertStockPrice(StockPrice stockPrice) {
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
    

}
