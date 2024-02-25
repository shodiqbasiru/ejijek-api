package com.enigma.enijek.service;

import com.enigma.enijek.entity.BrandMotorcycles;
import com.enigma.enijek.model.request.BrandRequest;
import com.enigma.enijek.model.response.BrandResponse;

import java.util.List;

public interface BrandService {
    BrandMotorcycles create(BrandRequest request);
    BrandResponse get(String brandId);
    List<BrandResponse> getAllBrands();
    List<BrandResponse> getBrandByNameOrModel(String brandName, String modelName);
    List<BrandResponse> getAllByBrandName(String brandName);
    BrandMotorcycles update(BrandRequest request , String brandId);
    BrandMotorcycles delete(String brandId);
}
