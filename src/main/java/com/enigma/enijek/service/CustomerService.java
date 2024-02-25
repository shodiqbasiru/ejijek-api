package com.enigma.enijek.service;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.model.request.CustomerRequest;
import com.enigma.enijek.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    void create(CustomerRequest request);
    CustomerResponse get(String customerId);
    List<CustomerResponse> getAllCustomers();
    List<CustomerResponse> findByName(String name);
    void update(CustomerRequest request , String customerId);
    void delete(String customerId);
}
