package org.example.repository;

import org.example.model.entity.CustomerEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("database")
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
}
