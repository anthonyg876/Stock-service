package com.stocks.stockprices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return ResponseEntity.status(HttpStatus.OK).body(prices);
    }
    
    
}
