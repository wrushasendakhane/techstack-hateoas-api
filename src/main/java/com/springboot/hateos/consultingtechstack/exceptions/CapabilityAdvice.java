package com.springboot.hateos.consultingtechstack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CapabilityAdvice {

  @ResponseBody
  @ExceptionHandler(CapabilityNotFoundException.class)
  public final ResponseEntity<CapabilityNotFoundExceptionResponse> capabilityNotFoundExceptionResponse(
      CapabilityNotFoundException ex) {
    CapabilityNotFoundExceptionResponse response = new CapabilityNotFoundExceptionResponse(ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ResponseBody
  @ExceptionHandler(CapabilityAlreadyExistsException.class)
  public final ResponseEntity<CapabilityAlreadyExistsExceptionResponse> capabilityAlreadyExistsExceptionResponse(
      CapabilityAlreadyExistsException ex) {
    CapabilityAlreadyExistsExceptionResponse response = new CapabilityAlreadyExistsExceptionResponse(ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
