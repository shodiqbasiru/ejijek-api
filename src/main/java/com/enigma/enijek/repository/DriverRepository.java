package com.enigma.enijek.repository;

import com.enigma.enijek.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver,String> {
    @Query("FROM Driver WHERE driverName LIKE %:driverName%")
    List<Driver> findByDriverName(String driverName);
}
