package com.stocks.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/stocks")
public class StockController {

    @Autowired
    private StockService stockService;


    @GetMapping("/getStocks")
    public ResponseEntity<List<Stock>> getStocks() {
        List<Stock> stocks = stockService.selectStocks();
        if (stocks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(stocks);
    }

    @GetMapping("/getIds")
    public ResponseEntity<List<String>> getIds() {
        List<String> ids = stockService.getStockIds();
        if (ids.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ids);
    }

    @GetMapping("/getNames")
    public ResponseEntity<?> getNames() {
        List<String> names = stockService.getStockNames();
        if (names.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(names);
    }

    @GetMapping("/loadData")
    public void loadStocks() {
        stockService.loadStocks();
    }

}
