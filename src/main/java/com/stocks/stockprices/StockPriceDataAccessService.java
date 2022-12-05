package com.stocks.stockprices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String sql = "select dateOfPrice, open, high, low, adjClosed, volume, companyId from stockPrices where companyid = ?";
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
        String sql = "insert into stockprices(dateOfPrice, open, high, low, adjClosed, volume, companyId) values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
            sql,
            stockPrice.getDateOfPrice(), stockPrice.getOpen(), stockPrice.getHigh(),
            stockPrice.getLow(), stockPrice.getAdjClosed(), stockPrice.getVolume(), stockPrice.getCompanyId()
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
        String sql = "insert into stockprices(dateOfPrice, open, high, low, adjClosed, volume, companyId) values(?, ?, ?, ?, ?, ?, ?)";
        
        ArrayList<Object[]> sqlArgs = new ArrayList<>();

        for (StockPrice stockPrice: stockPrices) {
            Object[] stockPriceData = {stockPrice.getDateOfPrice(), stockPrice.getOpen(), stockPrice.getHigh(),
                                    stockPrice.getLow(), stockPrice.getAdjClosed(), stockPrice.getVolume(), stockPrice.getCompanyId()}
                                    ;
            sqlArgs.add(stockPriceData);
        }

        jdbcTemplate.batchUpdate(sql, sqlArgs);
        
    }

    @Override
    public double averageVolumeOfStock(String id, String begin, String end) {
        String sql = "SELECT AVG(VOLUME) FROM stockprices WHERE companyID = ? and DATEOFPRICE >= ? and DATEOFPRICE <= ?";
        double averageVolume = 0;
        try {
            averageVolume =  jdbcTemplate.queryForObject(sql, Double.class, id, begin, end);
            return averageVolume;        
        } catch (Exception e) {
            e.printStackTrace();
            return averageVolume;
        }
    }

    @Override
    public double averageClosingPrice(String id, String begin, String end) {
        String sql = "SELECT avg(adjClosed) FROM stockprices WHERE companyID = ? and DATEOFPRICE >= ? and DATEOFPRICE <= ?";
        double avgClose = 0;
        try {
            avgClose = jdbcTemplate.queryForObject(sql, Double.class, id, begin, end);
            return avgClose;
        } catch (Exception e) {
            e.printStackTrace();
            return avgClose;
        }
    }

    @Override
    public int getTotalTuples() {
        String sql = "select count(*) from stockPrices";
        int tuplesCount = 0;
        try {
            tuplesCount =  jdbcTemplate.queryForObject(sql, Integer.class);
            return tuplesCount;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> getStockPriceUpdates(String id, String begin, String end) {
        String sql = "SELECT DATEOFPRICE, (ADJCLOSED - OPEN) AS \"DPC\" FROM agravier.stockprices WHERE companyID = ? and DATEOFPRICE >= ? and DATEOFPRICE < ? ORDER BY DATEOFPRICE ASC";
        List<Map<String, Object>> priceChanges;
        try {
            priceChanges = jdbcTemplate.queryForList(sql, id, begin, end);
            return priceChanges;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getPricePercentageChanges(String id, String begin, String end) {
        String sql = "SELECT DATEOFPRICE, 100 - ((OPEN / ADJCLOSED) * 100) AS \"PC\" FROM agravier.stockprices WHERE companyID = ? and DATEOFPRICE >= ? and DATEOFPRICE < ? ORDER BY DATEOFPRICE ASC";
        List<Map<String, Object>> priceChanges;
        try {
            priceChanges = jdbcTemplate.queryForList(sql, id, begin, end);
            return priceChanges;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StockPriceResult> getHighestGrowingStocks(String begin, String end, String index) {
        List<StockPriceResult> highestGrownStocks = new ArrayList<StockPriceResult>();

        String sql = "select fullName from stock where symbol in " +
        "(select companyId from " + 
            "(SELECT  CompanyID, (sum(100 - ((OPEN / ADJCLOSED) * 100))) AS \"growthInYear\" " +
                "FROM agravier.stockprices " + 
                "WHERE DATEOFPRICE >= ? and DATEOFPRICE < ? and companyId in (select symbol from stock where inDJI = ?) " + 
                "group by CompanyID " + 
                "order by \"growthInYear\" desc " +
                "fetch first 5 rows only))";

            String sql2 = "select \"growthInYear\" from " + 
            "(SELECT  CompanyID, (sum(100 - ((OPEN / ADJCLOSED) * 100))) AS \"growthInYear\" " +
                "FROM agravier.stockprices " + 
                "WHERE DATEOFPRICE >= ? and DATEOFPRICE < ? and companyId in (select symbol from stock where inDJI = ?) " + 
                "group by CompanyID " + 
                "order by \"growthInYear\" desc " +
                "fetch first 5 rows only)";
            try {
                List<String> namesOfStocks = jdbcTemplate.queryForList(sql, String.class, begin, end, index);
                List<Double> percentageIncreases = jdbcTemplate.queryForList(sql2, Double.class, begin, end, index);
                for (int i = 0; i < namesOfStocks.size(); i++) {
                    String name = namesOfStocks.get(i);
                    double percentIncrease = percentageIncreases.get(i).doubleValue();
                    highestGrownStocks.add(new StockPriceResult(name, percentIncrease));
                } 
            }
            catch (Exception e) {
                System.out.println("Could not perform query to get fullNames.");
                return null;
            }
            return highestGrownStocks;
    }

    @Override
    public Map<String, Object> highestGrownStocksInMarket(String begin, String end) {
        Map<String, Object> stocks = new HashMap<String, Object>();
        String sql = "select fullname, \"growthInYear\" from " + 
        "(SELECT s.CompanyID, (sum(100 - ((OPEN / ADJCLOSED) * 100))) AS \"growthInYear\" " +
        "FROM agravier.stockprices s " + 
        "WHERE DATEOFPRICE >= ? and DATEOFPRICE < ? " +
        "group by CompanyID " +
        "order by \"growthInYear\" desc " +
        "fetch first 5 rows only) s, stock where stock.symbol = s.companyID";
        try {
            stocks = jdbcTemplate.queryForMap(sql, begin, end);
            return stocks;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
