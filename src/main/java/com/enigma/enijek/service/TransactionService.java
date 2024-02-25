package com.enigma.enijek.service;

import com.enigma.enijek.entity.Order;
import com.enigma.enijek.model.request.OrderRequest;
import com.enigma.enijek.model.response.OrderInfoResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    Order create(OrderRequest request);
    List<OrderInfoResponse> getAllTransactions();
    Page<OrderInfoResponse> getAllTransactionsWithPagination(Integer pageNumber, Integer pageSize, String sort);
}
