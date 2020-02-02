package com.heycar.demo.domain.service;

import com.heycar.demo.domain.model.Item;
import java.util.List;

public interface InventoryService {

  void saveItems(List<Item> items, String dealerId);
}
