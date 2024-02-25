package com.enigma.enijek.controller;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.model.request.CustomerRequest;
import com.enigma.enijek.model.response.CustomerResponse;
import com.enigma.enijek.model.response.ResponseHandler;
import com.enigma.enijek.routes.RouteApi;
import com.enigma.enijek.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Add data
    @PostMapping(
            path = RouteApi.POST_CUSTOMER,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody CustomerRequest request) {
        try {
            Customer result = customerService.create(request);
            return ResponseHandler.generateResponse(
                    "New Customer Added",
                    HttpStatus.CREATED,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // find By id
    @GetMapping(
            path = RouteApi.GET_CUSTOMER,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> get(@PathVariable String customerId) {
        try {
            CustomerResponse result = customerService.get(customerId);
            return ResponseHandler.generateResponse(
                    "Success",
                    HttpStatus.CREATED,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // get All data
    @GetMapping(
            path = RouteApi.GET_CUSTOMERS,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getAllCustomers() {
        try {
            List<CustomerResponse> result = customerService.getAllCustomers();
            return ResponseHandler.generateResponse(
                    "Success",
                    HttpStatus.CREATED,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // get by name
    @GetMapping(
            path = RouteApi.GET_CUSTOMERS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "name"
    )
    public ResponseEntity<Object> findByName(@RequestParam String name) {
        try {
            List<CustomerResponse> result = customerService.findByName(name);
            return ResponseHandler.generateResponse(
                    "Success",
                    HttpStatus.CREATED,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }


    // update data
    @PutMapping(
            path = RouteApi.PUT_CUSTOMER,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> update(@RequestBody CustomerRequest request, @PathVariable String customerId) {
        try {
            Customer result = customerService.update(request, customerId);
            return ResponseHandler.generateResponse(
                    "Updated Data Successfully",
                    HttpStatus.OK,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // delete data
    @DeleteMapping(
            path = RouteApi.DELETE_CUSTOMER
    )
    public ResponseEntity<Object> delete(@PathVariable String customerId) {
        try {
            Customer result = customerService.delete(customerId);
            return ResponseHandler.generateResponse(
                    "Deleted Data Successfully",
                    HttpStatus.OK,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }
}
