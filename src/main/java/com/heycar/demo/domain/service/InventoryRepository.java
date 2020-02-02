package com.heycar.demo.domain.service;

import com.heycar.demo.domain.model.Item;
import com.heycar.demo.domain.model.SearchItem;
import java.util.List;

public interface InventoryRepository {


   void saveItems(List<Item> items, String dealerId);
   List<Item> fetchItems(SearchItem make);


}
