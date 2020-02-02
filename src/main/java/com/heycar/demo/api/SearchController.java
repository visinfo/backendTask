package com.heycar.demo.api;

import com.heycar.demo.domain.model.Item;
import com.heycar.demo.domain.model.SearchItem;
import com.heycar.demo.domain.service.InventoryService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/1.0/")
public class SearchController {

  private  final InventoryService inventoryService;

  @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<Item>> searchItem(@RequestParam(name = "make", required = false) @Valid  String make,@RequestParam(name = "model",required = false) @Valid String model, @RequestParam(name = "year",required = false) @Valid String year,@RequestParam(name = "color",required = false) @Valid String color){
    SearchItem searchItem = new SearchItem();
    searchItem.setMaker(make);
    searchItem.setModel(model);
    searchItem.setColor(color);
    searchItem.setModelYear(year);
    List<Item> items =inventoryService.fetchItems(searchItem);
    return new ResponseEntity<>(items, HttpStatus.OK);
  }
}
