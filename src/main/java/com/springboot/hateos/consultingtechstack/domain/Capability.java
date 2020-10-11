package com.springboot.hateos.consultingtechstack.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capability extends RepresentationModel<Capability> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "TechStack cannot be blank")
  @NotNull(message = "TechStack cannot be null")
  @Column(unique = true)
  private String techStack;
  private Integer noOfDevelopers = 0;
  private Integer noOfAvailableDevelopers = 0;

  public Capability(String teckStack, Integer noOfDevelopers, Integer noOfAvailableDevelopers) {
    this.techStack = teckStack;
    this.noOfDevelopers = noOfDevelopers;
    this.noOfAvailableDevelopers = noOfAvailableDevelopers;
  }

  @AssertTrue(message = "Available developers should be less than no of developers.")
  private boolean isNoOfAvailableDevelopers() {
    return this.noOfAvailableDevelopers <= this.noOfDevelopers;
  }

}
