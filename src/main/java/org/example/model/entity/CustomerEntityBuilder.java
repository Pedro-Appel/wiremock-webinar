package org.example.model.entity;


public class CustomerEntityBuilder {

    static CustomerEntity customer;

    public static CustomerEntityBuilder builder() {
        customer = new CustomerEntity();
        return new CustomerEntityBuilder();
    }

    public CustomerEntityBuilder name(String name) {
        customer.setName(name);
        return this;
    }

    public CustomerEntityBuilder documentNumber(String documentNumber) {
        customer.setDocumentNumber(documentNumber);
        return this;
    }

    public CustomerEntityBuilder phone(String phone) {
        customer.setPhone(phone);
        return this;
    }

    public static Customer toCustomer(CustomerEntity toCustomer){
        Customer customer1 = new Customer();
        customer1.setPhone(toCustomer.getPhone());
        customer1.setDocumentNumber(toCustomer.getDocumentNumber());
        customer1.setName(toCustomer.getName());
        return customer1;
    }

    public static CustomerEntity fromCustomer(Customer fromCustomer){
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setPhone(fromCustomer.getPhone());
        customer1.setDocumentNumber(fromCustomer.getDocumentNumber());
        customer1.setName(fromCustomer.getName());
        return customer1;
    }

    public CustomerEntity build() {
        return customer;
    }
}
