package com.springboot.hateos.consultingtechstack.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import com.springboot.hateos.consultingtechstack.assembler.CapabilityResourceAssembler;
import com.springboot.hateos.consultingtechstack.domain.Capability;
import com.springboot.hateos.consultingtechstack.services.CapabilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class CapabilityController {

  @Autowired
  private CapabilityService capabilityService;

  @Autowired
  private CapabilityResourceAssembler assembler;

  @GetMapping("/{id}")
  public ResponseEntity<?> getCapability(@PathVariable Long id) {
    Capability capability = capabilityService.findCapById(id);
    // EntityModel<Capability> model = EntityModel.of(capability);
    // WebMvcLinkBuilder linkTo =
    // linkTo(methodOn(CapabilityController.class).getCapability(id));
    // model.add(linkTo.withSelfRel());
    // linkTo = linkTo(methodOn(CapabilityController.class).getAllCapabilities());
    // model.add(linkTo.withSelfRel());
    // return new ResponseEntity<>(model, HttpStatus.OK);
    return new ResponseEntity<>(assembler.toModel(capability), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<?> getAllCapabilities() {
    // List<Capability> allCapabilities = capabilityService.getAllCapabilities();
    // allCapabilities.forEach(cap -> {
    // cap.add(linkTo(methodOn(CapabilityController.class).getCapability(cap.getId())).withRel("getThisCapability"));
    // });
    // Link link =
    // linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("getAllCapabilities");
    // CollectionModel<Capability> model = CollectionModel.of(allCapabilities,
    // link);
    // return new ResponseEntity<>(model, HttpStatus.OK);
    return new ResponseEntity<>(assembler.toCollectionModel(capabilityService.getAllCapabilities()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> createCapability(@Valid @RequestBody Capability capability, BindingResult result) {

    if (result.hasErrors())
      return capabilityService.errorMap(result);

    return new ResponseEntity<>(assembler.toModel(capabilityService.saveCapability(capability)), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateCapability(@PathVariable Long id, @Valid @RequestBody Capability capability,
      BindingResult result) {
    if (result.hasErrors())
      return capabilityService.errorMap(result);
    return new ResponseEntity<>(assembler.toModel(capabilityService.updateCapability(capability)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCapability(@PathVariable Long id) {
    capabilityService.deleteCapability(id);
    return new ResponseEntity<>("Capability deleted", HttpStatus.OK);
  }
}
