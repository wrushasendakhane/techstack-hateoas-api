package com.springboot.hateos.consultingtechstack.repositories;

import com.springboot.hateos.consultingtechstack.domain.Capability;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityRepository extends CrudRepository<Capability, Long> {
  Iterable<Capability> findAllByOrderByIdDesc();
}
