package com.springboot.hateos.consultingtechstack.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapabilityAlreadyExistsExceptionResponse {
  private String techStack;
}
