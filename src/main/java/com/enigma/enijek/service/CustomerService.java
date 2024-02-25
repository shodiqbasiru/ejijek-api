package com.enigma.enijek.service;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.model.request.CustomerRequest;
import com.enigma.enijek.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    Customer create(CustomerRequest request);
    CustomerResponse get(String customerId);
    List<CustomerResponse> getAllCustomers();
    List<CustomerResponse> findByName(String name);
    Customer update(CustomerRequest request , String customerId);
    Customer delete(String customerId);
}
