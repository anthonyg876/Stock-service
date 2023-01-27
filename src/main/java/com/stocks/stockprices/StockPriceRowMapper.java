package com.stocks.stockprices;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class StockPriceRowMapper implements RowMapper<StockPrice> {

    @Override
    @Nullable
    public StockPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new StockPrice(
            rs.getDate("dateOfPrice"),
            rs.getDouble("low"),
            rs.getDouble("open"),
            rs.getInt("volume"),
            rs.getDouble("adjClosed"),
            rs.getString("companyId")
        );
    }
    
}
