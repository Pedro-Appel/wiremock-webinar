package org.example.standalone;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.example.clients.CustomerFeignClient;
import org.example.model.entity.CustomerEntity;
import org.example.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Objects;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles({"test", "services"})
@EnableConfigurationProperties
@SpringBootTest(classes = {WireMockConfig.class})
class CustomerTest {

    @Autowired
    private WireMockServer mockCustomersService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerFeignClient client;

    @BeforeEach
    void setUp() throws IOException {
        CustomerMocks.setupMockCustomerResponse(mockCustomersService);
    }

    @Test
    public void whenGetCustomers_thenSomethingShouldBeReturned() {
        assertFalse(Objects.requireNonNull(client.getCustomers().getBody()).isEmpty());
    }

    @Test
    public void whenGetCustomers_thenCustomerListShouldBeReturned() {
        assertTrue(customerService.getCustomers()
                .containsAll(asList(
                        new CustomerEntity("123e4567-e89b-12d3-a456-426614174000","987654321", "Frank Herbert", "+351 990 998 899"),
                        new CustomerEntity("123e4567-e89b-12d3-a456-426614174000","321654987", "Isaac Asimov", "+351 990 444 009"))));
    }
}