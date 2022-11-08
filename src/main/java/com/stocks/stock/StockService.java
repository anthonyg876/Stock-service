package com.stocks.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocks.stockprices.StockPrice;
import com.stocks.stockprices.StockPriceService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;
import java.text.DecimalFormat;

@Service
public class StockService {

    private static final DecimalFormat df = new DecimalFormat("0.00"); 

    @Autowired
    private StockPriceService stockPriceService;

    private final StockDao stockDao;

    public StockService(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public void addStock(Stock stock) {
        int result = stockDao.insertStock(stock);
        if (result != 1) {
            throw new IllegalStateException("Could not insert stock into the database.");
        }
    }

    public List<Stock> selectStocks() {
        return stockDao.selectStocks();
    }

    public void deleteStock(String id) {
        stockDao.deleteStock(id);
    }

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
        //Skip over the first line.
        scStockName.nextLine();
        
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

            // Get the symbol of the stock from the file. Ensure that they match.
            scStockName.next();
            String symbol = scStockName.next();
            String nameOfStock;
            String inDJI;
            String inDJGT;
            String inNDX;
            //Check if the symbols match up.
            while (true) {
                if (!symbol.equals(stockSymbol)) {
                    scStockName.nextLine();
                    scStockName.next();
                    symbol = scStockName.next();
                    continue;
                }
                // Get the fullname of the Stock.
                nameOfStock = scStockName.next();
                if (nameOfStock.contains("\"")) {
                    while (true) {
                        String fullName = scStockName.next();
                        nameOfStock = nameOfStock + fullName;
                        if (fullName.contains("\"")) {
                            break;
                        }
                    }
                }
                for (int j = 0; j < 9; j++) {
                    scStockName.next();
                }
                inDJGT = scStockName.next();
                inDJI = scStockName.next();
                inNDX = scStockName.nextLine();
                inNDX = inNDX.substring(1);
                break;
            }
            //Add stock to the database.
            addStock(new Stock(symbol, nameOfStock, inDJGT, inDJI, inNDX));

            //skip first Line
            sc.nextLine();

            ArrayList<StockPrice> stockPrices = new ArrayList<>();
            String date_;
            double open;
            double high;
            double low;
            double adjClose;
            int volume;
            String volume_;
            Date date;

            while (sc.hasNext()) {
                // If the input is null, skip the line.
                try {
                    date_ = sc.next();
                    open = sc.nextDouble();
                    high = sc.nextDouble();
                    low = sc.nextDouble();
                    sc.nextDouble();
                    adjClose = sc.nextDouble();
                    volume_ = sc.nextLine();    
                } catch (InputMismatchException ime) {
                    System.out.println("Skipped final data point for: " + symbol);
                    break;
                } 
                catch (Exception e) {
                    System.out.println("Skipped final data point for: " + symbol);
                    break;
                }                
                volume_ = volume_.substring(1, volume_.length());
                // Cast volume to int if the string contains a decimal point.
                if (volume_.contains(".")) {
                    double _volume = Double.parseDouble(volume_);
                    volume = (int)_volume;
                }
                else {
                    try {
                    volume = Integer.parseInt(volume_);
                    } catch (NumberFormatException ne) {
                        System.out.println("Format error for voluem occured on date: " + date_);
                        break;
                    }
                }
                // Ensure that the date value can be inserted.
                try {
                    date = Date.valueOf(date_);
                } catch (Exception e) {
                    System.out.println("Could not parse date: " + date_);
                    break;
                }
                StockPrice stockPrice = new StockPrice(date, low, open, volume, adjClose, symbol);
                stockPrices.add(stockPrice);
            }
            stockPriceService.addStockPrices(stockPrices);  
        }
    }
}
