package com.stocks.stockservice.stock;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;
import os;

@Service
public class StockService {

    public void loadStocks() {
        String directory = System.getProperty("user.dir");
        

        
        //Initial load of stocks if the tables are empty
        File dir = new File("data/stocks");
        String[] directoryListing =dir.list();

        //Get the names of the stocks and their id's
        for (int i = 0; i < directoryListing.length; i++) {
            String pathToFile = directoryListing[i];
            
            // Create scanner to get information
            Scanner sc;
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
            
            //skip first Line
            sc.nextLine();
            while (sc.hasNext()) {
                String date_ = sc.next();
                double open = sc.nextDouble();
                double high = sc.nextDouble();
                double low = sc.nextDouble();
                double adjClose = sc.nextDouble();
                int volume = sc.nextInt();

                Date date = Date.valueOf(date_);
                


                sc.nextLine();

            
            } 
            
        }
    }


}
