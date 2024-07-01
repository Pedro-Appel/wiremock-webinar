package org.example.standalone;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
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

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles({"test", "services"})
@EnableConfigurationProperties
@SpringBootTest(classes = {WireMockConfig.class})
class CustomerRequestValidationTest {

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
    public void requestMatchingTest() {
        customerService.getCustomers();
        mockCustomersService.verify(exactly(1), WireMock.getRequestedFor(WireMock.urlEqualTo("/customers")));
        mockCustomersService.verify(RequestPatternBuilder.newRequestPattern()
                .withUrl("/customers"));
    }
}