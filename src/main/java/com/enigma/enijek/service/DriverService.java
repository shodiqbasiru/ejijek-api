package com.enigma.enijek.service;

import com.enigma.enijek.entity.Driver;
import com.enigma.enijek.model.request.DriverRequest;
import com.enigma.enijek.model.response.DriverResponse;

import java.util.List;

public interface DriverService {
    Driver create(DriverRequest request);
    DriverResponse get(String driverId);
    List<DriverResponse> getAllDrivers();
    List<DriverResponse> getAllDriverByName(String driverName);
    Driver update(DriverRequest request, String driverId);
    Driver delete(String driverId);
}
