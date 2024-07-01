package org.example.service.impl;

import lombok.extern.log4j.Log4j2;
import org.example.clients.CustomerFeignClient;
import org.example.model.CustomerDTO;
import org.example.model.entity.Customer;
import org.example.model.entity.CustomerBuilder;
import org.example.model.entity.CustomerEntity;
import org.example.service.CustomerService;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Log4j2
public class ExternalServiceCustomerImpl implements CustomerService {

    private final CustomerFeignClient client;

    public ExternalServiceCustomerImpl(CustomerFeignClient client) {
        this.client = client;
    }

    @Override
    public List<CustomerEntity> getCustomers() {
        log.info("Calling for customers service");
        ResponseEntity<List<CustomerEntity>> customerResponse = client.getCustomers();

        return customerResponse.getBody();
    }

    @Override
    public String createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerBuilder.fromDto(customerDTO);
        CustomerEntity savedCustomer = client.createCustomer(customer);
        return savedCustomer.getUuid();
    }

    @Override
    public CustomerEntity getCustomerById(String customerDocumentNumber) {
        return client.getCustomerById(customerDocumentNumber);
    }
}
