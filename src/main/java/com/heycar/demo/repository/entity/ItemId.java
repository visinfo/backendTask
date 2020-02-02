package com.heycar.demo.repository.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemId implements Serializable {

  @Column(name = "dealer_id", nullable = false)
  private String dealerId;

  @Column(name = "item_code", nullable = false)
  private String code ;



  public  ItemId(String dealerId,String code){
    this.dealerId = dealerId;
    this.code = code ;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemId itemId = (ItemId) o;
    return Objects.equals(dealerId, itemId.dealerId) &&
        Objects.equals(code, itemId.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dealerId, code);
  }
}
