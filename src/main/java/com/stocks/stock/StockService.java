package com.stocks.stock;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

@Service
public class StockService {

    public void loadStocks() {
        //Initial load of stocks if the tables are empty
        File dir = new File("data/stocks");
        String[] directoryListing =dir.list();

        List<String> listings = Arrays.asList(directoryListing);
        listings.sort(null);
        
        //Create scanner to get stock information
        Scanner sc;

        // Create scanner to get stockNames.
        Scanner scStockName;
        try {
            scStockName = new Scanner(new File("data/symbols_valid_meta.csv"));
            scStockName.useDelimiter(",");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not load in data from stocks.");
            return;
        }
        
        //Iterate through the directory for the stocks.
        for (int i = 0; i < directoryListing.length; i++) {
            String pathToFile = directoryListing[i];
            pathToFile = "data/stocks/" + pathToFile;
            
            try {
                sc = new Scanner(new File(pathToFile));
                sc.useDelimiter(",");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Could not load in data from stocks.");
                return;
            }
            

            //Get the symbol of the stock.
            String stockSymbol = pathToFile.substring(12, pathToFile.length() -4);

            // Get the full name of the stock.
            scStockName.nextLine();
            scStockName.next();
            String symbol = scStockName.next();
            
            //Check if the symbols match up.
            while (true) {
                if (symbol.equals(stockSymbol)) {
                    scStockName.nextLine();
                    break;
                }
                scStockName.nextLine();
                scStockName.next();
                symbol = sc.next();
            }

            //skip first Line
            String value = sc.nextLine();
            while (sc.hasNext()) {
                String date_ = sc.next();
                double open = sc.nextDouble();
                double high = sc.nextDouble();
                double low = sc.nextDouble();
                sc.nextDouble();
                double adjClose = sc.nextDouble();
                String volume_ = sc.nextLine();
            
                volume_ = volume_.substring(1, volume_.length());
                int volume = Integer.parseInt(volume_);

                Date date = Date.valueOf(date_);
            } 
            
        }
    }


}
