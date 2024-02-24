package com.enigma.enijek.controller;

import com.enigma.enijek.model.response.BrandResponse;
import com.enigma.enijek.model.response.WebResponse;
import com.enigma.enijek.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    // get All data
    @GetMapping(
            path = "/api/brands",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<BrandResponse>>> getAllBrands(){
        List<BrandResponse> brandResponses = brandService.getAllBrands();
        return new ResponseEntity<>(WebResponse.<List<BrandResponse>>builder().data(brandResponses).build(), HttpStatus.OK);
    }

}
