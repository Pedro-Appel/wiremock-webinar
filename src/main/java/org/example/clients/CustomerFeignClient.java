package org.example.clients;

import feign.Headers;
import org.example.model.entity.Customer;
import org.example.model.entity.CustomerEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "customer", url = "${customer.url}")
public interface CustomerFeignClient {

    @GetMapping("/customers")
    ResponseEntity<List<CustomerEntity>> getCustomers();

    @PostMapping(value = "/customers", consumes = "application/json", produces = "application/json")
    @Headers("Content-Type: application/json")
    CustomerEntity createCustomer(Customer customer);

    @GetMapping(value = "/customers/{id}",consumes = "application/json", produces = "application/json")
    CustomerEntity getCustomerById(@PathVariable("id") String number);
}

