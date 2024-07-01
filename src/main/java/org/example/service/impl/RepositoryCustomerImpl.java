package org.example.service.impl;

import lombok.extern.log4j.Log4j2;
import org.example.model.CustomerDTO;
import org.example.model.entity.CustomerEntity;
import org.example.model.entity.CustomerEntityBuilder;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;

import java.util.List;

@Log4j2
public class RepositoryCustomerImpl implements CustomerService {

    private final CustomerRepository repository;

    public RepositoryCustomerImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerEntity> getCustomers(){
        log.info("Searching for customer in database");
        return repository.findAll();
    }

    @Override
    public String createCustomer(CustomerDTO customerDTO) {
        CustomerEntity customer = CustomerEntityBuilder.builder()
                .name(customerDTO.name())
                .documentNumber(customerDTO.documentNumber())
                .phone(customerDTO.phone())
                .build();
        return repository.saveAndFlush(customer).getDocumentNumber();
    }

    @Override
    public CustomerEntity getCustomerById(String customerDocumentNumber) {
        return repository.findById("1234").orElseThrow();
    }
}
