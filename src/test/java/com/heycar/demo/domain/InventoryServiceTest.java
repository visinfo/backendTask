package com.heycar.demo.domain;

import com.heycar.demo.domain.exception.BusinessException;
import com.heycar.demo.domain.model.Item;
import com.heycar.demo.domain.service.InventoryService;
import com.heycar.demo.domain.service.InventoryServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryServiceTest {

  private InventoryService inventoryService;

  @BeforeEach
  public  void setup(){
    inventoryService = new InventoryServiceImpl();
  }

  @Test
  public void saveItem_checkIfSaveItemSuccessfully(){
    List<Item> itemList = getItemList();
    inventoryService.saveItems(itemList, "121212");

  }
  @Test
  public void saveItem_checkIfThrowExceptionWhenDealerIdIsBlank(){
    List<Item> itemList = getItemList();
    Assertions.assertThrows(BusinessException.class, () ->inventoryService.saveItems(itemList, ""));

  }
  private List<Item> getItemList() {
    List<Item> itemList = new ArrayList<>();
    Item item = new Item();
    item.setCode("1");
    item.setMaker("mercedes");
    item.setModel("a");
    item.setColor("black");
    item.setEnginePower("180");
    item.setModelYear("2014");
    item.setPrice(new BigDecimal(15950));
    itemList.add(item);
    return  itemList;
  }

}
