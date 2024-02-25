package com.enigma.enijek.repository;

import com.enigma.enijek.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    @Query("FROM Customer WHERE customerName LIKE %:name%")
    List<Customer> findByName(String name);
}
