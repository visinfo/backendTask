package com.heycar.demo.domain.service;

import com.heycar.demo.domain.exception.BusinessException;
import com.heycar.demo.domain.exception.ServiceErrorCode;
import com.heycar.demo.domain.model.Item;
import com.heycar.demo.domain.model.SearchItem;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  @Override
  public void saveItems(List<Item> items, String dealerId) {
    if(dealerId==null||dealerId.trim().isEmpty()){
      throw new BusinessException(ServiceErrorCode.INVALID_DEALER_ID);
    }
    if(items==null||items.isEmpty()){
      throw new BusinessException(ServiceErrorCode.ITEM_LIST_BLANK);
    }
    inventoryRepository.saveItems(items,dealerId);
  }

  @Override
  public List<Item> fetchItems(SearchItem make) {
    if(make==null||make.isEmpty()){
      throw new BusinessException(ServiceErrorCode.INVALID_QUERY);
    }
    return inventoryRepository.fetchItems(make);
  }
}
