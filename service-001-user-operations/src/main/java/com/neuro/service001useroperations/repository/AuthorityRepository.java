package com.neuro.service001useroperations.repository;

import com.neuro.service001useroperations.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
