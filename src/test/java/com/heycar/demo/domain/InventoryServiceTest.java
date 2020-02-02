package com.heycar.demo.domain;

import static org.mockito.ArgumentMatchers.any;

import com.heycar.demo.domain.exception.BusinessException;
import com.heycar.demo.domain.model.Item;
import com.heycar.demo.domain.model.SearchItem;
import com.heycar.demo.domain.service.InventoryRepository;
import com.heycar.demo.domain.service.InventoryServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class InventoryServiceTest {

  @InjectMocks
  private InventoryServiceImpl inventoryService;
  @Mock
  private InventoryRepository inventoryRepository;

  @BeforeEach
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
    Mockito.doNothing().when(inventoryRepository).saveItems(any(List.class),any(String.class));
    Mockito.when(inventoryRepository.fetchItems(any(SearchItem.class))).thenReturn(getItemList());

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

  @Test
  public void fetchItem_checkIfThrowExceptionWhenSearchItemIsNull(){
    SearchItem searchItem= new SearchItem();
    Assertions.assertThrows(BusinessException.class, () ->inventoryService.fetchItems(searchItem));

  }

  @Test
  public void fetchItem_checkIfFetchItemSuccessfully(){
    SearchItem searchItem= new SearchItem();
    searchItem.setColor("black");
    List<Item> items = inventoryService.fetchItems(searchItem);
    Assertions.assertEquals(items.size(),1);

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
