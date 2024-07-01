package org.example.configuration;

import feign.Logger;
import org.example.clients.CustomerFeignClient;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.example.service.impl.ExternalServiceCustomerImpl;
import org.example.service.impl.RepositoryCustomerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CustomerServiceConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    @Profile("database")
    CustomerService getCustomerRepositoryService(CustomerRepository customerRepository) {
        return new RepositoryCustomerImpl(customerRepository);
    }

    @Bean
    @Profile("services")
    CustomerService getCustomerClientService(CustomerFeignClient feignClient) {
        return new ExternalServiceCustomerImpl(feignClient);
    }
}
