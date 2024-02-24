package com.enigma.enijek.service.impl;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.model.request.CustomerRequest;
import com.enigma.enijek.model.response.CustomerResponse;
import com.enigma.enijek.reppsitory.CustomerRepository;
import com.enigma.enijek.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public void create(CustomerRequest request){
        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());

        customerRepository.save(customer);
    }

    @Override
    public CustomerResponse get(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .customerName(customer.getCustomerName())
                        .phoneNumber(customer.getPhoneNumber())
                        .address(customer.getAddress())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(CustomerRequest request , String customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Id Not Found"));

        customer.setCustomerName(request.getCustomerName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());

        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(String customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Id Not Found"));
        customerRepository.delete(customer);
    }
}
