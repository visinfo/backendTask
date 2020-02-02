package com.heycar.demo.domain.exception;

import lombok.Getter;

@Getter
public enum  ServiceErrorCode {

  INVALID_DEALER_ID(1300,"Invalid Dealer Id"),
  ITEM_LIST_BLANK ( 1400,"Item List cannot be blank "),
  INVALID_QUERY( 1500,"Invalid Query Parameters");

  private Integer code;
  private String message;

  ServiceErrorCode(Integer code,String message){
    this.code=code;
    this.message=message;
  }
}
