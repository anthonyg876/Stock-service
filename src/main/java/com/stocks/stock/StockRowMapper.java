package com.stocks.stock;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class StockRowMapper implements RowMapper<Stock> {

    @Override
    @Nullable
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Stock(
                rs.getString("symbol"),
                rs.getString("fullName"),
                rs.getString("inDJGT"),
                rs.getString("inDJI"),
                rs.getString("inNDX")
                );
    }
    
}
