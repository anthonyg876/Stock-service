package com.stocks.index;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class IndexService {
    
    private final IndexDao indexDao;

    public IndexService(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    public List<Index> getIndices() {
        return indexDao.getIndices();
    }

    public void addNewIndex(Index index) {
        // @TODO: check in the index exists
        int result = indexDao.insertIndex(index);
        if (result != 1) {
            throw new IllegalStateException("Error inserting index into database.");
        } 
    }

    public void deleteIndex(String id) {
        Optional<Index> indices = indexDao.selectIndexById(id);
        indices.ifPresentOrElse(Index -> {
            int result = indexDao.deleteIndex(id);
            if (result != 1) {
                throw new IllegalStateException("Could not delete index");
            }
        }, () -> {
            throw new IllegalStateException(String.format("Index with id %s not found", id));
        });
    }


    public Index getIndex(String id) {
        Index index;
        try {
        Optional<Index> index_ = indexDao.selectIndexById(id);
        index = index_.get();
        } catch (Exception e) {
            e.printStackTrace();
            index = null;
        }
        return index;
    }

    public void addIndices() {
        Scanner sc;

        String id;
        int numberOfStocks;
        String name;

        Index index;
        
        try {
            sc = new Scanner(new File("data/indices.csv"));
            sc.useDelimiter(",");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not load in indices.csv");
            return;
        }
        //Skip the first Line.
        sc.nextLine();

        //Load in the values.
        while (sc.hasNext()) {
            id = sc.next();
            numberOfStocks = sc.nextInt();
            name = sc.nextLine();
            name = name.substring(1, name.length());
            index = new Index(id, numberOfStocks, name);
            
            // Add index to the database.
            addNewIndex(index);
        }

        //Read in the indices data.

    }
}
