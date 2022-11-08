package com.stocks.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/indices")
public class IndexController {

    @Autowired
    private IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping
    public List<Index> listIndices() {
        return indexService.getIndices();
    }

    @PostMapping
    public void addIndex(@RequestBody Index index) {
        indexService.addNewIndex(index);
    }

    @DeleteMapping("/{id}")
    public void deleteIndex(@PathVariable("id") String id) {
        indexService.deleteIndex(id);
    }

    @GetMapping("/loadData")
    public void addIndices() {
        indexService.addIndices();
    }



    
}
