package org.example.service;

import org.example.model.CustomerDTO;
import org.example.model.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {

    List<CustomerEntity> getCustomers();

    String createCustomer(CustomerDTO customerDTO);

    CustomerEntity getCustomerById(String customerDocumentNumber);
}
