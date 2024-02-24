package com.enigma.enijek.service.impl;

import com.enigma.enijek.entity.BrandMotorcycles;
import com.enigma.enijek.model.request.BrandRequest;
import com.enigma.enijek.model.response.BrandResponse;
import com.enigma.enijek.model.response.BrandInfoResponse;
import com.enigma.enijek.reppsitory.BrandRepository;
import com.enigma.enijek.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void create(BrandRequest request) {
        BrandMotorcycles brandMotorcycles = new BrandMotorcycles();
        brandMotorcycles.setBrandName(request.getBrandName());
        brandMotorcycles.setModelName(request.getModelName());

        brandRepository.save(brandMotorcycles);
    }

    @Override
    public BrandResponse get(String brandId) {
        BrandMotorcycles response = brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException(HttpStatus.UNAUTHORIZED + "Id Not Found"));
        return BrandResponse.builder()
                .id(response.getId())
                .brandName(response.getBrandName())
                .modelName(response.getBrandName())
                .driverList(getInfoDriver(response).collect(Collectors.toList()))
                .build();
    }

    private Stream<BrandInfoResponse> getInfoDriver(BrandMotorcycles response) {
        return response.getDrivers().stream()
                .map(driver -> BrandInfoResponse.builder()
                        .id(driver.getId())
                        .driverName(driver.getDriverName())
                        .licensePlate(driver.getLicensePlate())
                        .phoneNumber(driver.getPhoneNumber())
                        .build());
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        List<BrandMotorcycles> response = brandRepository.findAll();
        return response.stream()
                .map(brand -> BrandResponse.builder()
                        .id(brand.getId())
                        .brandName(brand.getBrandName())
                        .modelName(brand.getModelName())
                        .driverList(getInfoDriver(brand).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(BrandRequest request, String brandId) {
        BrandMotorcycles brandMotorcycles = brandRepository.findById(brandId).orElseThrow(() ->new RuntimeException(HttpStatus.UNAUTHORIZED+" ID Not Found"));

        brandMotorcycles.setBrandName(request.getBrandName());
        brandMotorcycles.setModelName(request.getModelName());

        brandRepository.save(brandMotorcycles);
    }

    @Override
    @Transactional
    public void delete(String brandId) {
        BrandMotorcycles brandMotorcycles = brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException(HttpStatus.UNAUTHORIZED + " Id Not Found"));
        brandRepository.delete(brandMotorcycles);
    }


}
