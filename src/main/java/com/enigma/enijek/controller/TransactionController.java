package com.enigma.enijek.controller;

import com.enigma.enijek.entity.Order;
import com.enigma.enijek.model.request.OrderDetailRequest;
import com.enigma.enijek.model.request.OrderRequest;
import com.enigma.enijek.model.response.OrderDetailResponse;
import com.enigma.enijek.model.response.OrderInfoResponse;
import com.enigma.enijek.model.response.WebResponse;
import com.enigma.enijek.routes.RouteApi;
import com.enigma.enijek.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // create transaction
    @PostMapping(
            path = RouteApi.POST_TRANSACTION,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> create(@RequestBody OrderRequest request){
        transactionService.create(request);
        return new ResponseEntity<>(
                WebResponse.<String>builder().data("New Transaction Added Successfully").build(),
                HttpStatus.CREATED
        );
    }

    // get all transaction
    @GetMapping(
            path = RouteApi.GET_TRANSACTION,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<OrderInfoResponse>>> getAllTransactions(){
        List<OrderInfoResponse> responseList = transactionService.getAllTransactions();
        return new ResponseEntity<>(
                WebResponse.<List<OrderInfoResponse>>builder()
                        .data(responseList)
                        .build(),
                HttpStatus.OK
        );
    }
}
