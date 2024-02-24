package com.enigma.enijek.service;

import com.enigma.enijek.entity.Driver;
import com.enigma.enijek.model.request.DriverRequest;
import com.enigma.enijek.model.response.DriverResponse;

import java.util.List;

public interface DriverService {
    void create(DriverRequest request);
    DriverResponse get(String driverId);
    List<DriverResponse> getAllDrivers();
    void update(DriverRequest request, String driverId);
    void delete(String driverId);
}
