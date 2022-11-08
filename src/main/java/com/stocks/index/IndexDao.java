package com.stocks.index;

import java.util.List;
import java.util.Optional;

public interface IndexDao {

    List<Index> getIndices();
    int insertIndex(Index index);
    int deleteIndex(String id);
    Optional<Index> selectIndexById(String id);
    
}
