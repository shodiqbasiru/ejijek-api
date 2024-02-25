package com.enigma.enijek.controller;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.model.request.CustomerRequest;
import com.enigma.enijek.model.response.CustomerResponse;
import com.enigma.enijek.model.response.WebResponse;
import com.enigma.enijek.routes.RouteApi;
import com.enigma.enijek.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
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
    public ResponseEntity<WebResponse<String>> create(@RequestBody CustomerRequest request){
        customerService.create(request);
        WebResponse<String> response = WebResponse.<String>builder().data("New Customer Added Successfully").build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // find By id
    @GetMapping(
            path = RouteApi.GET_CUSTOMER,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> get(@PathVariable String customerId) {
        CustomerResponse customerResponse = customerService.get(customerId);
        return WebResponse.<CustomerResponse>builder().data(customerResponse).build();
    }

    // get All data
    @GetMapping(
            path = RouteApi.GET_CUSTOMERS,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<CustomerResponse>>> getAllCustomers() {
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();
        WebResponse<List<CustomerResponse>> webResponse = WebResponse.<List<CustomerResponse>>builder().data(customerResponses).build();
        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }

    // get by name
    @GetMapping(
            path = RouteApi.GET_CUSTOMERS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "name"
    )
    public ResponseEntity<WebResponse<List<CustomerResponse>>> findByName(@RequestParam String name){
        List<CustomerResponse> customerName = customerService.findByName(name);
        if (customerName.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                WebResponse.<List<CustomerResponse>>builder().data(customerName).build()
        );
    }


    // update data
    @PutMapping(
            path = RouteApi.PUT_CUSTOMER,
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
            path = RouteApi.DELETE_CUSTOMER
    )
    public ResponseEntity<WebResponse<String>> delete(@PathVariable String customerId){
        customerService.delete(customerId);
        WebResponse<String> webResponse = WebResponse.<String>builder().data("Customer deleted successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }

}
