package org.example.model.entity;


import org.example.model.CustomerDTO;

public class CustomerBuilder {

    static Customer customer;

    public static CustomerBuilder builder() {
        customer = new Customer();
        return new CustomerBuilder();
    }

    public CustomerBuilder name(String name) {
        customer.setName(name);
        return this;
    }

    public CustomerBuilder documentNumber(String documentNumber) {
        customer.setDocumentNumber(documentNumber);
        return this;
    }

    public CustomerBuilder phone(String phone) {
        customer.setPhone(phone);
        return this;
    }

    public static CustomerDTO toDto(CustomerEntity customerToDto){
        return new CustomerDTO(customerToDto.getName(), customerToDto.getPhone(), customerToDto.getDocumentNumber());
    }

    public static Customer fromDto(CustomerDTO customerFromDto){
        Customer customer1 = new Customer();
        customer1.setPhone(customerFromDto.phone());
        customer1.setDocumentNumber(customerFromDto.documentNumber());
        customer1.setName(customerFromDto.name());
        return customer1;
    }

    public Customer build() {
        return customer;
    }
}
