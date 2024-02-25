package com.enigma.enijek.service.impl;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.entity.Driver;
import com.enigma.enijek.entity.Order;
import com.enigma.enijek.entity.OrderDetail;
import com.enigma.enijek.model.request.OrderDetailRequest;
import com.enigma.enijek.model.request.OrderRequest;
import com.enigma.enijek.model.response.OrderDetailResponse;
import com.enigma.enijek.model.response.OrderInfoResponse;
import com.enigma.enijek.repository.CustomerRepository;
import com.enigma.enijek.repository.DriverRepository;
import com.enigma.enijek.repository.OrderDetailRepository;
import com.enigma.enijek.repository.OrderRepository;
import com.enigma.enijek.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public TransactionServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, OrderDetailRepository orderDetailRepository, DriverRepository driverRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional
    public Order create(OrderRequest request) {

        Order order = new Order();
        order.setDate(request.getDate());

        Customer customer = customerRepository.findById(String.valueOf(request.getCustomerId())).orElseThrow(() -> new RuntimeException("Customer Id Not Found"));
        order.setCustomer(customer);

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailRequest detailRequest : request.getOrderDetailRequests()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);

            Driver driver = driverRepository.findById(String.valueOf(detailRequest.getDriverId())).orElseThrow(() -> new RuntimeException("Driver ID Not Found"));

            orderDetail.setEntryPoint(detailRequest.getEntryPoint());
            orderDetail.setEndPoint(detailRequest.getEndPoint());
            orderDetail.setDistance(detailRequest.getDistance());
            orderDetail.setDriver(driver);

            orderDetails.add(orderDetail);
        }

        order.setOrderDetailEntityList(orderDetails);
        return orderRepository.save(order);
    }

    @Override
    public List<OrderInfoResponse> getAllTransactions() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream()
                .map(order -> OrderInfoResponse.builder()
                        .id(order.getId())
                        .date(order.getDate())
                        .customerId(order.getCustomer().getId())
                        .orderDetails(order.getOrderDetailEntityList().stream()
                                .map(listDetail -> OrderDetailResponse.builder()
                                        .id(listDetail.getId())
                                        .driverId(listDetail.getDriver().getId())
                                        .entryPoint(listDetail.getEntryPoint())
                                        .endPoint(listDetail.getEndPoint())
                                        .distance(listDetail.getDistance())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderInfoResponse> getAllTransactionsWithPagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            // with sorting
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            // without sorting
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return orderRepository.findAll(pageable)
                .map(order -> OrderInfoResponse.builder()
                        .id(order.getId())
                        .date(order.getDate())
                        .customerId(order.getCustomer().getId())
                        .orderDetails(order.getOrderDetailEntityList().stream()
                                .map(listDetail -> OrderDetailResponse.builder()
                                        .id(listDetail.getId())
                                        .driverId(listDetail.getDriver().getId())
                                        .entryPoint(listDetail.getEntryPoint())
                                        .endPoint(listDetail.getEndPoint())
                                        .distance(listDetail.getDistance())
                                        .build())
                                .collect(Collectors.toList()))
                        .build());
    }
}
