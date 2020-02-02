package com.heycar.demo.domain.service;

import com.heycar.demo.domain.exception.BusinessException;
import com.heycar.demo.domain.exception.ServiceErrorCode;
import com.heycar.demo.domain.model.Item;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {

  @Override
  public void saveItems(List<Item> items, String dealerId) {
    if(dealerId==null||dealerId.trim().isEmpty()){
      throw new BusinessException(ServiceErrorCode.INVALID_DEALER_ID);
    }
    if(items==null||items.isEmpty()){
      throw new BusinessException(ServiceErrorCode.ITEM_LIST_BLANK);
    }
  }
}
