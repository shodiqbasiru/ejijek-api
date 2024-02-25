package com.enigma.enijek.service.impl;

import com.enigma.enijek.entity.BrandMotorcycles;
import com.enigma.enijek.entity.Driver;
import com.enigma.enijek.model.request.DriverRequest;
import com.enigma.enijek.model.response.DriverInfoResponse;
import com.enigma.enijek.model.response.DriverResponse;
import com.enigma.enijek.repository.BrandRepository;
import com.enigma.enijek.repository.DriverRepository;
import com.enigma.enijek.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, BrandRepository brandRepository) {
        this.driverRepository = driverRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void create(DriverRequest request) {
        Driver driver = new Driver();
        getReqDriver(request, driver);
    }

    private void getReqDriver(DriverRequest request, Driver driver) {
        driver.setDriverName(request.getDriverName());
        driver.setLicensePlate(request.getLicensePlate());
        driver.setPhoneNumber(request.getPhoneNumber());

        BrandMotorcycles brand = brandRepository.findById(String.valueOf(request.getMotorcycleId())).orElseThrow(() -> new RuntimeException("Id Not Found"));

        driver.setBrandMotorcycles(brand);
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

    @Override
    public List<DriverResponse> getAllDriverByName(String driverName) {
        List<Driver> responseList = driverRepository.findByDriverName(driverName);
        return responseList.stream()
                .map(driver -> DriverResponse.builder()
                        .id(driver.getId())
                        .driverName(driver.getDriverName())
                        .licensePlate(driver.getLicensePlate())
                        .phoneNumber(driver.getPhoneNumber())
                        .motorcycles(mapBrandMotorcycles(driver.getBrandMotorcycles()))
                        .build())
                .collect(Collectors.toList());
    }

    private DriverInfoResponse mapBrandMotorcycles(BrandMotorcycles brandMotorcycles) {
        if (brandMotorcycles == null) {
            return null;
        }
        return DriverInfoResponse.builder()
                .id(brandMotorcycles.getId())
                .brandName(brandMotorcycles.getBrandName())
                .modelName(brandMotorcycles.getModelName())
                .build();
    }

    @Override
    @Transactional
    public void update(DriverRequest request, String driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(()-> new RuntimeException("Id Not Found"));
        getReqDriver(request, driver);
    }

    @Override
    @Transactional
    public void delete(String driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Id Not Found"));
        driverRepository.delete(driver);
    }
}
