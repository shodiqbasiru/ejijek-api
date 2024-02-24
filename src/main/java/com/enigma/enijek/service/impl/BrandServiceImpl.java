package com.enigma.enijek.service.impl;

import com.enigma.enijek.entity.BrandMotorcycles;
import com.enigma.enijek.model.request.BrandRequest;
import com.enigma.enijek.model.response.BrandResponse;
import com.enigma.enijek.model.response.DriverResponse;
import com.enigma.enijek.reppsitory.BrandRepository;
import com.enigma.enijek.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void create(BrandRequest request) {

    }

    @Override
    public BrandResponse get(String brandId) {
        return null;
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        List<BrandMotorcycles> response = brandRepository.findAll();
        return response.stream()
                .map(brand -> BrandResponse.builder()
                        .id(brand.getId())
                        .brandName(brand.getBrandName())
                        .modelName(brand.getModelName())
                        .driverList(brand.getDrivers()
                                .stream().map(driver -> DriverResponse.builder()
                                        .id(driver.getId())
                                        .driverName(driver.getDriverName())
                                        .licensePlate(driver.getLicensePlate())
                                        .phoneNumber(driver.getPhoneNumber())
                                        .build())
                                .collect(Collectors.toList())
                        )
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void update(BrandRequest request, String brandId) {

    }

    @Override
    public void delete(String brandId) {

    }
}
