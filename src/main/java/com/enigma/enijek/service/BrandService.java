package com.enigma.enijek.service;

import com.enigma.enijek.model.request.BrandRequest;
import com.enigma.enijek.model.response.BrandResponse;

import java.util.List;

public interface BrandService {
    void create(BrandRequest request);
    BrandResponse get(String brandId);
    List<BrandResponse> getAllBrands();
    void update(BrandRequest request , String brandId);
    void delete(String brandId);
}
