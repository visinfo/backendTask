package com.heycar.demo.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

  private final ServiceErrorCode serviceErrorCode;

}
