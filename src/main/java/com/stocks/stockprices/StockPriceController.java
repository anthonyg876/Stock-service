package com.stocks.stockprices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/stockPrices")
public class StockPriceController {

    @Autowired
    private StockPriceService stockPriceService;

    @GetMapping("/getPrices/{id}")
    public ResponseEntity<?> getStockPrices(@PathVariable String id) {
        List<StockPrice> prices = stockPriceService.getStockPrices(id); 
        if (prices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find the prices for the given stock.");
        }
        System.out.println("Fetched data from: " + id);       
        return ResponseEntity.status(HttpStatus.OK).body(prices);
    }
    @GetMapping("/totalTuples")
    public ResponseEntity<?> getTotalTuples() {
        int totalCount = stockPriceService.getTotalTuples();
        if (totalCount != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(totalCount);
        }
        System.out.println("Couldn't retrieve the number of tuples in the database");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't retrieve the number of tuples in the database");
    }

/** All the commands for the 5 final queries are here*/
    @PostMapping("/averageVolume") 
    public ResponseEntity<?> getAverageVolume(@RequestBody ArrayList<String> stockPriceInfo) {
        double averageVolume = stockPriceService.getAverageVolumeOfStock(stockPriceInfo.get(0), stockPriceInfo.get(1), stockPriceInfo.get(2));
        return ResponseEntity.status(HttpStatus.OK).body(averageVolume);
        }

    @PostMapping("/averageClose")
    public ResponseEntity<?> averageClose(@RequestBody ArrayList<String> stockPriceInfo) {
        double avgClose = stockPriceService.getAverageClosingPrice(stockPriceInfo.get(0), stockPriceInfo.get(1), stockPriceInfo.get(2));
        if (avgClose == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not get the average Stock Price from: " + stockPriceInfo.get(0));
        }
        return ResponseEntity.status(HttpStatus.OK).body(avgClose);
    }

    @PostMapping("/priceChanges")
    public ResponseEntity<?> priceChanges(@RequestBody ArrayList<String> stockPriceInfo) {
        List<Map<String, Object>> priceChanges = stockPriceService.getPriceChanges(stockPriceInfo.get(0), stockPriceInfo.get(1), stockPriceInfo.get(2));
        if (priceChanges.size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not get the price changes from: " + stockPriceInfo.get(0));
        }
        return ResponseEntity.status(HttpStatus.OK).body(priceChanges);
    }

    @PostMapping("/percentageChanges")
    public ResponseEntity<?> percentageChanges(@RequestBody ArrayList<String> stockPriceInfo) {
        List<Map<String, Object>> percentageChanges = stockPriceService.getPercentageChanges(stockPriceInfo.get(0), stockPriceInfo.get(1), stockPriceInfo.get(2));
        if (percentageChanges.size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not get the price changes from: " + stockPriceInfo.get(0));
        }
        return ResponseEntity.status(HttpStatus.OK).body(percentageChanges);
    }
    @PostMapping("/highestGrowingStocks")
    public ResponseEntity<?> highestGrowingStocks(@RequestBody ArrayList<String> stockPriceInfo) {
        Map<String, Double> highestGrownStocks = stockPriceService.getHighestGrowingStocks(stockPriceInfo.get(0), stockPriceInfo.get(1), stockPriceInfo.get(2));
        if (highestGrownStocks.size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not get the price changes from: " + stockPriceInfo.get(0));
        }
        return ResponseEntity.status(HttpStatus.OK).body(highestGrownStocks);
    } 

    
}
