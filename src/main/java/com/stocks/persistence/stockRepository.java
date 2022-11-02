package com.stocks.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stocks.stockservice.stock.Stock;

@Repository
public class stockRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Stock stock) {
        String sql = "insert into stock" + "(";
    }
}
