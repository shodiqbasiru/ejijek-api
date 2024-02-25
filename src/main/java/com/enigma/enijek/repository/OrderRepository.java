package com.enigma.enijek.repository;

import com.enigma.enijek.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
