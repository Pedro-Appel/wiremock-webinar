package org.example.controller;

import org.example.model.CustomerDTO;
import org.example.model.entity.CustomerEntity;
import org.example.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        String id = service.createCustomer(customerDTO);
        return ResponseEntity.created(URI.create("/api/v1/customers/" + id)).build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerEntity>> listCustomers() {
        return ResponseEntity.ok(service.getCustomers());
    }
}
