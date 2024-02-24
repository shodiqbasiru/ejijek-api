package com.enigma.enijek.service.impl;

import com.enigma.enijek.entity.BrandMotorcycles;
import com.enigma.enijek.entity.Driver;
import com.enigma.enijek.model.request.DriverRequest;
import com.enigma.enijek.model.response.BrandResponse;
import com.enigma.enijek.model.response.DriverResponse;
import com.enigma.enijek.reppsitory.DriverRepository;
import com.enigma.enijek.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional
    public void create(DriverRequest request) {
        Driver driver = new Driver();
        driver.setDriverName(request.getDriverName());
        driver.setLicensePlate(request.getLicensePlate());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setBrandMotorcycles(request.getMotorcycles());
        driverRepository.save(driver);
    }

    @Override
    public DriverResponse get(String driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Id Not Found"));

        return DriverResponse.builder()
                .id(driver.getId())
                .driverName(driver.getDriverName())
                .licensePlate(driver.getLicensePlate())
                .phoneNumber(driver.getPhoneNumber())
                .motorcycles(mapBrandMotorcycles(driver.getBrandMotorcycles()))
                .build();
    }


    @Override
    public List<DriverResponse> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();

        return drivers.stream()
                .map(driver -> DriverResponse.builder()
                        .id(driver.getId())
                        .driverName(driver.getDriverName())
                        .licensePlate(driver.getLicensePlate())
                        .phoneNumber(driver.getPhoneNumber())
                        .motorcycles(mapBrandMotorcycles(driver.getBrandMotorcycles()))
                        .build())
                .collect(Collectors.toList());
    }

    private BrandResponse mapBrandMotorcycles(BrandMotorcycles brandMotorcycles) {
        if (brandMotorcycles == null) {
            return null;
        }
        return BrandResponse.builder()
                .id(brandMotorcycles.getId())
                .brandName(brandMotorcycles.getBrandName())
                .modelName(brandMotorcycles.getModelName())
                .build();
    }

    @Override
    @Transactional
    public void update(DriverRequest request, String driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(()-> new RuntimeException("Id Not Found"));

        driver.setDriverName(request.getDriverName());
        driver.setLicensePlate(request.getLicensePlate());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setBrandMotorcycles(request.getMotorcycles());

        driverRepository.save(driver);
    }

    @Override
    @Transactional
    public void delete(String driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Id Not Found"));
        driverRepository.delete(driver);
    }
}
