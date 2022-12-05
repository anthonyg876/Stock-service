package com.stocks.index;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IndexDataAccessService implements IndexDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public IndexDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Index> getIndices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertIndex(Index index) {
        String sql = "insert into agravier.stockindex(id, numberOfStocks, name) values(?, ?, ?)";
        return jdbcTemplate.update(
            sql,
            index.getId(), index.getNumberOfStocks(), index.getName()
            );
    }

    @Override
    public int deleteIndex(String id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<Index> selectIndexById(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
