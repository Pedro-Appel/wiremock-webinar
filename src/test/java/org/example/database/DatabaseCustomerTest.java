package org.example.database;

import org.example.model.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ActiveProfiles({"test", "database"})
@SpringBootTest
public class DatabaseCustomerTest {
    @Autowired
    private CustomerService customerService;
    @MockBean
    private CustomerRepository repository;

    @Test
    void listCustomers(){
        when(repository.findAll()).thenReturn(Arrays.asList(
                new CustomerEntity("987654321", "Frank Herbert", "+351 990 998 899"),
                new CustomerEntity("321654987", "Isaac Asimov", "+351 990 444 009")));
        customerService.getCustomers();
    }
}
