package org.example.standalone;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.example.model.CustomerDTO;
import org.example.model.entity.CustomerEntity;
import org.example.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Random;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles({"test", "services"})
@EnableConfigurationProperties
@SpringBootTest(classes = {WireMockConfig.class})
class WiremockWithJsonTests {

    @Autowired
    private WireMockServer mockCustomerServiceWithJsonStubbing;

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        mockCustomerServiceWithJsonStubbing.resetAll();
    }

    @Test
    public void stubBehaviorTest() {
        for (int i = 0; i < 3; i++) {
            customerService.getCustomerById("123554");
        }
    }

    @Test
    public void stubDynamicTest() {
        for (int i = 0; i < 3; i++) {
            int customerDocumentNumber = new Random().nextInt(0, 9999999);
            customerService.getCustomerById(String.valueOf(customerDocumentNumber));
        }
    }

    @Test
    public void stubPreDefinedTest() {
        CustomerEntity customerById = customerService.getCustomerById("9999");
        mockCustomerServiceWithJsonStubbing.verify(
                exactly(1),
                getRequestedFor(urlMatching("/customers/9999"))
        );
        Assertions.assertEquals(customerById.getName(), "randomName");
    }

    @Test
    public void postRequestMatchingTest() {
        String customerId = customerService.createCustomer(stubClient());
        Assertions.assertNotEquals("00904a20-ddf8-4982-92b4-36f610ecd454", customerId);
    }

    private CustomerDTO stubClient() {
        return new CustomerDTO("Pedro", "phone", "documentNumber");
    }
}