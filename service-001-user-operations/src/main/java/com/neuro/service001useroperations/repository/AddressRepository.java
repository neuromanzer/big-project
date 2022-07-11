package com.neuro.service001useroperations.repository;

import com.neuro.service001useroperations.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
