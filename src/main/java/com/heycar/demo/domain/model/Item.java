package com.heycar.demo.domain.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
public class Item {

  private String code ;

  private String maker ;

  private String model ;

  private String enginePower;

  private String modelYear;

  private String color ;

  private BigDecimal price ;

}
