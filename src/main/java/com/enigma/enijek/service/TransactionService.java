package com.enigma.enijek.service;

import com.enigma.enijek.model.request.OrderRequest;
import com.enigma.enijek.model.response.OrderInfoResponse;

import java.util.List;

public interface TransactionService {
    void create(OrderRequest request);
    List<OrderInfoResponse> getAllTransactions();
}
