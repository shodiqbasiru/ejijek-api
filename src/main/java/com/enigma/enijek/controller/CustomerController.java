package com.enigma.enijek.controller;

import com.enigma.enijek.model.request.CustomerRequest;
import com.enigma.enijek.model.response.CustomerResponse;
import com.enigma.enijek.model.response.WebResponse;
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
            path = "/api/customers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> create(@RequestBody CustomerRequest request){
        customerService.create(request);
        WebResponse<String> response = WebResponse.<String>builder().data("New Customer Added Successfully").build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // find By id
    @GetMapping(
            path = "/api/customers/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> get(@PathVariable String customerId) {
        CustomerResponse customerResponse = customerService.get(customerId);
        return WebResponse.<CustomerResponse>builder().data(customerResponse).build();
    }


    // get All data
    @GetMapping(
            path = "/api/customers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<CustomerResponse>>> getAllCustomers() {
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();
        WebResponse<List<CustomerResponse>> webResponse = WebResponse.<List<CustomerResponse>>builder().data(customerResponses).build();
        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }

    // update data
    @PutMapping(
            path = "/api/customers/{customerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> update(@RequestBody CustomerRequest request, @PathVariable String customerId){
        customerService.update(request,customerId);

        WebResponse<String> webResponse = WebResponse.<String>builder().data("Customer updated successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }

    // delete data
    @DeleteMapping(
            path = "/api/customers/{customerId}"
    )
    public ResponseEntity<WebResponse<String>> delete(@PathVariable String customerId){
        customerService.delete(customerId);
        WebResponse<String> webResponse = WebResponse.<String>builder().data("Customer deleted successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }

}
