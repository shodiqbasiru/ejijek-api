package com.enigma.enijek.controller;

import com.enigma.enijek.model.request.BrandRequest;
import com.enigma.enijek.model.response.BrandResponse;
import com.enigma.enijek.model.response.WebResponse;
import com.enigma.enijek.routes.RouteApi;
import com.enigma.enijek.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    // add Data
    @PostMapping(
            path = RouteApi.POST_BRAND,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> create(@RequestBody BrandRequest request){
        brandService.create(request);
        return new ResponseEntity<>(
                WebResponse.<String>builder().data("New Brand Added Successfully").build(),
                HttpStatus.CREATED
        );
    }

    // findById
    @GetMapping(
            path = RouteApi.GET_BRAND,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<BrandResponse>> get(@PathVariable String brandId){
        BrandResponse response = brandService.get(brandId);
        return new ResponseEntity<>(
                WebResponse.<BrandResponse>builder().data(response).build(),
                HttpStatus.OK
        );
    }

    // get All data
    @GetMapping(
            path = RouteApi.GET_BRANDS,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<BrandResponse>>> getAllBrands(){
        List<BrandResponse> brandResponses = brandService.getAllBrands();
        return new ResponseEntity<>(
                WebResponse.<List<BrandResponse>>builder().data(brandResponses).build(),
                HttpStatus.OK
        );
    }

    // get brand by brandName and modelName
    @GetMapping(
            path = RouteApi.GET_BRANDS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"brand","model"}
    )
    public ResponseEntity<WebResponse<List<BrandResponse>>> getBrandByNameAndModel(@RequestParam String brand, @RequestParam String model){
        List<BrandResponse> responseList = brandService.getBrandByNameOrModel(brand, model);
        return new ResponseEntity<>(
                WebResponse.<List<BrandResponse>>builder().data(responseList).build(),
                HttpStatus.OK
        );
    }

    //get brand by brandName
    @GetMapping(
            path = RouteApi.GET_BRANDS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "brand"
    )
    public ResponseEntity<WebResponse<List<BrandResponse>>> getByBrandName(@RequestParam String brand){
        List<BrandResponse> responseList = brandService.getAllByBrandName(brand);
        return new ResponseEntity<>(
                WebResponse.<List<BrandResponse>>builder()
                        .data(responseList)
                        .build(),
                HttpStatus.OK
        );
    }

    // update data
    @PutMapping(
            path = RouteApi.PUT_BRAND,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> update(@RequestBody BrandRequest request, @PathVariable String brandId) {
        brandService.update(request, brandId);
        return new ResponseEntity<>(
                WebResponse.<String>builder().data("Updated Data Successfully").build(),
                HttpStatus.OK
        );
    }

    // delete
    @DeleteMapping(
            path = RouteApi.DELETE_BRAND
    )
    public ResponseEntity<WebResponse<String>> delete(@PathVariable String brandId) {
        brandService.delete(brandId);
        return new ResponseEntity<>(
                WebResponse.<String>builder().data("Deleted Data Successfully").build(),
                HttpStatus.OK
        );

    }

}
