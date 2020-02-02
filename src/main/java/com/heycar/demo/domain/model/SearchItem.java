package com.heycar.demo.domain.model;

import lombok.Data;

@Data
public class SearchItem {

  private String color ;

  private String maker ;

  private String model ;

  private String modelYear;

  public boolean isEmpty() {
    return (this.color == null && this.maker == null && this.model ==null && this.modelYear== null);
  }
}
