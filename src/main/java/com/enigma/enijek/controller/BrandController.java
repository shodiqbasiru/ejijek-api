package com.enigma.enijek.controller;

import com.enigma.enijek.entity.BrandMotorcycles;
import com.enigma.enijek.model.request.BrandRequest;
import com.enigma.enijek.model.response.BrandResponse;
import com.enigma.enijek.model.response.ResponseHandler;
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
    public ResponseEntity<Object> create(@RequestBody BrandRequest request){
        try{
            BrandMotorcycles result = brandService.create(request);
            return ResponseHandler.generateResponse(
                    "New Brand Added Successfully",
                    HttpStatus.CREATED,
                    result
            );
        }catch (Exception e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // findById
    @GetMapping(
            path = RouteApi.GET_BRAND,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> get(@PathVariable String brandId){
        try{
            BrandResponse response = brandService.get(brandId);
            return ResponseHandler.generateResponse(
                    "Get Data By Id Successfully",
                    HttpStatus.OK,
                    response
            );
        }catch (Exception e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // get All data
    @GetMapping(
            path = RouteApi.GET_BRANDS,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getAllBrands(){
        try{
            List<BrandResponse> response= brandService.getAllBrands();
            return ResponseHandler.generateResponse(
                    "Get All Data Successfully",
                    HttpStatus.OK,
                    response
            );
        }catch (Exception e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // get brand by brandName and modelName
    @GetMapping(
            path = RouteApi.GET_BRANDS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"brand","model"}
    )
    public ResponseEntity<Object> getBrandByNameAndModel(@RequestParam String brand, @RequestParam String model){
        try {
            List<BrandResponse> response = brandService.getBrandByNameOrModel(brand, model);
            return ResponseHandler.generateResponse(
                    "Success",
                    HttpStatus.OK,
                    response
            );
        }catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    //get brand by brandName
    @GetMapping(
            path = RouteApi.GET_BRANDS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "brand"
    )
    public ResponseEntity<Object> getByBrandName(@RequestParam String brand){
       try {
           List<BrandResponse> response = brandService.getAllByBrandName(brand);
           return ResponseHandler.generateResponse(
                   "UPDATED",
                   HttpStatus.OK,
                   response
           );
       }catch (Exception e) {
           return ResponseHandler.generateResponse(
                   e.getMessage(),
                   HttpStatus.MULTI_STATUS,
                   null
           );
       }
    }

    // update data
    @PutMapping(
            path = RouteApi.PUT_BRAND,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> update(@RequestBody BrandRequest request, @PathVariable String brandId) {
        try {
            BrandMotorcycles response = brandService.update(request, brandId);
            return ResponseHandler.generateResponse(
                    "DELETED",
                    HttpStatus.OK,
                    response
            );
        }catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // delete
    @DeleteMapping(
            path = RouteApi.DELETE_BRAND
    )
    public ResponseEntity<Object> delete(@PathVariable String brandId) {
        try {
        BrandMotorcycles response = brandService.delete(brandId);
            return ResponseHandler.generateResponse(
                    "Success",
                    HttpStatus.OK,
                    response
            );
        }catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

}
