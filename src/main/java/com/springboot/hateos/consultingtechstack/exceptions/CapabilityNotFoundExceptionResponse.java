package com.springboot.hateos.consultingtechstack.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapabilityNotFoundExceptionResponse {
  private String capabilityNotFound;
}
