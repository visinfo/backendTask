package com.heycar.demo.repository;

import com.heycar.demo.domain.model.Item;
import com.heycar.demo.domain.model.SearchItem;
import com.heycar.demo.domain.service.InventoryRepository;
import com.heycar.demo.repository.entity.Inventory;
import com.heycar.demo.repository.entity.ItemId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class H2Repository implements InventoryRepository {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void saveItems(List<Item> items, String dealerId) {
    for (Item item : items
    ) {
      ItemId itemId = new ItemId(dealerId, item.getCode());

      Inventory inv = entityManager.find(Inventory.class, itemId, LockModeType.OPTIMISTIC);
      if (inv == null) {
        addInventory(item, itemId);
      } else {
        updateInventory(item, inv);
      }

    }
  }

  @Override
  public List<Item> fetchItems(SearchItem make) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Inventory> query = builder.createQuery(Inventory.class);
    Root r = query.from(Inventory.class);
    Predicate predicate = getPredicate(make, builder, r);
    query.where(predicate);
    List<Inventory> inventories =entityManager.createQuery(query).getResultList();
    return inventories.stream().map(inventory -> {
      Item item =new Item();
      item.setColor(inventory.getColor());
      item.setModelYear(inventory.getModelYear());
      item.setPrice(inventory.getPrice());
      item.setEnginePower(inventory.getEnginePower());
      item.setCode(inventory.getItemId().getCode());
      item.setMaker(inventory.getMaker());
      item.setModel(inventory.getModel());
      return  item;}).collect(
        Collectors.toList());
  }

  private Predicate getPredicate(SearchItem make, CriteriaBuilder builder, Root r) {
    Predicate predicate = builder.conjunction();

    if(make.getColor()!=null && !make.getColor().trim().isEmpty()){
      predicate= builder.and(predicate, builder.equal(
          r.get("color"), make.getColor()));
    }
    if(make.getModelYear()!=null && !make.getModelYear().trim().isEmpty()){
      predicate= builder.and(predicate, builder.equal(
          r.get("modelYear"), make.getModelYear()));
    }
    if(make.getModel()!=null && !make.getModel().trim().isEmpty()){
      predicate= builder.and(predicate, builder.equal(
          r.get("model"), make.getModel()));
    }
    if(make.getMaker()!=null && !make.getMaker().trim().isEmpty()){
      predicate= builder.and(predicate, builder.equal(
          r.get("maker"), make.getMaker()));
    }


    return predicate;
  }

  private void updateInventory(Item item, Inventory inv) {
    inv.setColor(item.getColor());
    inv.setEnginePower(item.getEnginePower());
    inv.setMaker(item.getMaker());
    inv.setModel(item.getModel());
    inv.setPrice(item.getPrice());
    inv.setModelYear(item.getModelYear());
    inv.setUpdatedAt(LocalDateTime.now());
    entityManager.merge(inv);
  }

  private void addInventory(Item item, ItemId itemId) {
    Inventory inventory = new Inventory();
    inventory.setItemId(itemId);
    inventory.setColor(item.getColor());
    inventory.setEnginePower(item.getEnginePower());
    inventory.setMaker(item.getMaker());
    inventory.setModel(item.getModel());
    inventory.setPrice(item.getPrice());
    inventory.setModelYear(item.getModelYear());
    inventory.setUpdatedAt(LocalDateTime.now());
    inventory.setCreatedAt(LocalDateTime.now());
    entityManager.persist(inventory);
  }



}
