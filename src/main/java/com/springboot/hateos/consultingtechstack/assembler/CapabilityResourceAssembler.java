package com.springboot.hateos.consultingtechstack.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import com.springboot.hateos.consultingtechstack.domain.Capability;
import com.springboot.hateos.consultingtechstack.resources.CapabilityController;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CapabilityResourceAssembler implements RepresentationModelAssembler<Capability, EntityModel<Capability>> {

  @Override
  public EntityModel<Capability> toModel(Capability entity) {

    EntityModel<Capability> model = EntityModel.of(entity);

    model.add(linkTo(methodOn(CapabilityController.class).getCapability(entity.getId())).withRel("getThisCapability"),
        linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("getAllCapability"),
        linkTo(methodOn(CapabilityController.class).deleteCapability(entity.getId())).withRel("deleteThisCapability"),
        linkTo(methodOn(CapabilityController.class).updateCapability(entity.getId(), entity, null))
            .withRel("updateThisCapability"));
    return model;
  }

  @Override
  public CollectionModel<EntityModel<Capability>> toCollectionModel(Iterable<? extends Capability> entities) {

    List<EntityModel<Capability>> listModels = new ArrayList<>();
    entities.forEach(entity -> {
      listModels.add(toModel(entity));
    });
    Link link = linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("getAllCapabilities");
    Link createlink = linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("createCapability");

    CollectionModel<EntityModel<Capability>> model = CollectionModel.of(listModels, link, createlink);

    return model;

  }

}