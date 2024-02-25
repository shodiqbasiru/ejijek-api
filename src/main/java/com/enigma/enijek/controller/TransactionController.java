package com.enigma.enijek.controller;

import com.enigma.enijek.entity.Order;
import com.enigma.enijek.model.request.OrderRequest;
import com.enigma.enijek.model.response.OrderInfoResponse;
import com.enigma.enijek.model.response.ResponseHandler;
import com.enigma.enijek.routes.RouteApi;
import com.enigma.enijek.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> create(@RequestBody OrderRequest request) {
        try {
            Order result = transactionService.create(request);
            return ResponseHandler.generateResponse(
                    "New Transaction Added Successfully",
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

    // get all transaction
    @GetMapping(
            path = RouteApi.GET_TRANSACTION,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getAllTransactions() {
        try {
            List<OrderInfoResponse> result = transactionService.getAllTransactions();
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

    // pagination
    @GetMapping(
            path = RouteApi.GET_TRANSACTION,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"page", "size"}
    )
    public ResponseEntity<Object> getOrder(@RequestParam Integer page, @RequestParam Integer size) {
        try {
            Page<OrderInfoResponse> result = transactionService.getAllTransactionsWithPagination(page, size, null);
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

    @GetMapping(
            path = RouteApi.GET_TRANSACTION,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"page", "size", "sort"}
    )
    public ResponseEntity<Object> getOrder(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort) {
        try {
            Page<OrderInfoResponse> result = transactionService.getAllTransactionsWithPagination(page, size, sort);
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
}