package com.springboot.hateos.consultingtechstack.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.springboot.hateos.consultingtechstack.domain.Capability;
import com.springboot.hateos.consultingtechstack.exceptions.CapabilityAlreadyExistsException;
import com.springboot.hateos.consultingtechstack.exceptions.CapabilityNotFoundException;
import com.springboot.hateos.consultingtechstack.repositories.CapabilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class CapabilityService {

  @Autowired
  private CapabilityRepository capabilityRepository;

  public List<Capability> getAllCapabilities() {
    Iterable<Capability> it = capabilityRepository.findAllByOrderByIdDesc();
    List<Capability> capabilities = new ArrayList<>();
    it.forEach(cap -> capabilities.add(cap));
    return capabilities;
  }

  public Capability findCapById(Long id) {
    return capabilityRepository.findById(id)
        .orElseThrow(() -> new CapabilityNotFoundException("Capability with ID: " + id + " Not found"));
  }

  public Capability saveCapability(Capability capability) {
    try {
      capability.setTechStack(capability.getTechStack().toUpperCase());
      return capabilityRepository.save(capability);
    } catch (Exception e) {
      throw new CapabilityAlreadyExistsException("Capability " + capability.getTechStack() + " already exists");
    }
  }

  public Capability updateCapability(Capability capability) {
    return capabilityRepository.findById(capability.getId()).map(cap -> {
      cap.setTechStack(capability.getTechStack());
      cap.setNoOfDevelopers(capability.getNoOfDevelopers());
      cap.setNoOfAvailableDevelopers(capability.getNoOfAvailableDevelopers());
      return capabilityRepository.save(cap);
    }).orElseGet(() -> {
      return capabilityRepository.save(capability);
    });
  }

  public void deleteCapability(Long id) {
    capabilityRepository.delete(capabilityRepository.findById(id)
        .orElseThrow(() -> new CapabilityNotFoundException("Capability with ID: " + id + " Not found")));
  }

  public ResponseEntity<?> errorMap(BindingResult result) {
    var errorMap = new HashMap<>();
    for (FieldError error : result.getFieldErrors()) {
      errorMap.put(error.getField(), error.getDefaultMessage());
    }
    return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
  }
}
