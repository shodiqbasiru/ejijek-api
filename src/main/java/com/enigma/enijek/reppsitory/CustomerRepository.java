package com.enigma.enijek.reppsitory;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.model.response.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    @Query("FROM Customer WHERE customerName LIKE %:name%")
    List<Customer> findByName(String name);
}
