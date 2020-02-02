package com.heycar.demo.api;

import com.heycar.demo.domain.model.Item;
import com.heycar.demo.domain.service.InventoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/1.0/")
public class ListingController {

  private  final InventoryService inventoryService;

  @RequestMapping(value = "/vehicles/{dealerId}/listings", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<?> createListing(@PathVariable("dealerId") String dealerId, @RequestBody List<Item> items){
    inventoryService.saveItems(items,dealerId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
