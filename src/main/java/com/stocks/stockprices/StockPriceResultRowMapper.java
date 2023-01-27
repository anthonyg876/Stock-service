package com.stocks.stockprices;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class StockPriceResultRowMapper implements RowMapper<StockPriceResult> {

    @Override
    @Nullable
    public StockPriceResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new StockPriceResult(
            rs.getString("fullName"),
            rs.getDouble("growthInYear")
        );
    }
}