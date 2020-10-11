package com.springboot.hateos.consultingtechstack.data;

import com.springboot.hateos.consultingtechstack.domain.Capability;
import com.springboot.hateos.consultingtechstack.services.CapabilityService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class InitialDataLoad {

  @Bean
  CommandLineRunner LoadDB(CapabilityService capabilityService) {
    return args -> {
      capabilityService.saveCapability(new Capability("Java", 30, 10));
      capabilityService.saveCapability(new Capability("ReactJs", 15, 4));
      capabilityService.saveCapability(new Capability("Python", 23, 7));
    };
  }
}
