package com.heycar.demo.api;

import com.heycar.demo.api.common.CSVListingParser;
import com.heycar.demo.domain.model.Item;
import com.heycar.demo.domain.service.InventoryService;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
  @RequestMapping(value = "/upload_csv/{dealerId}/listings", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<?>  uploadListing(@PathVariable("dealerId") String dealerId, @RequestParam("file") MultipartFile file)
      throws IOException {

    File tempFile = File.createTempFile(dealerId, ".csv");
    tempFile.deleteOnExit();
    // transfer MultipartFile to File
    file.transferTo(tempFile);

    List<Item> items = getItems(tempFile);
    inventoryService.saveItems(items, dealerId);

    tempFile.delete();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private List<Item> getItems(File tempFile) throws IOException {
    List<List<String>> listingCsvResources = CSVListingParser.parseFile(tempFile);
    List<Item> items = new ArrayList<>();
    for (List<String> listing : listingCsvResources) {
      Item item = new Item();
      item.setCode(listing.get(0));
      item.setMaker(listing.get(1).split("/")[0]);
      item.setModel(listing.get(1).split("/")[1]);
      item.setEnginePower(listing.get(2));
      item.setModelYear(listing.get(3));
      item.setColor(listing.get(4));
      item.setPrice(new BigDecimal(listing.get(5)));
      items.add(item);
    }
    return items;
  }
}
