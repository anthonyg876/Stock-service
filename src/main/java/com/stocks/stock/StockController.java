package com.stocks.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/stocks")
public class StockController {

    @Autowired
    private StockService stockService;


    @GetMapping("/getStocks")
    public void getStocks() {
        List<Stock> stocks = stockService.selectStocks();
        System.out.println(stocks.get(1).getFullName());
    }

    @GetMapping("/loadData")
    public void loadStocks() {
        stockService.loadStocks();
    }


}
