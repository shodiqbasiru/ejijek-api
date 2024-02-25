package com.enigma.enijek.controller;

import com.enigma.enijek.entity.Driver;
import com.enigma.enijek.model.request.DriverRequest;
import com.enigma.enijek.model.response.DriverResponse;
import com.enigma.enijek.model.response.ResponseHandler;
import com.enigma.enijek.routes.RouteApi;
import com.enigma.enijek.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // add Data
    @PostMapping(
            path = RouteApi.POST_DRIVER,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody DriverRequest request) {
        try {
            Driver result = driverService.create(request);
            return ResponseHandler.generateResponse(
                    "New Customer Added Successfully",
                    HttpStatus.CREATED,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // find by id
    @GetMapping(
            path = RouteApi.GET_DRIVER,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> get(@PathVariable String driverId) {
        try {
            DriverResponse result = driverService.get(driverId);
            return ResponseHandler.generateResponse(
                    "Success",
                    HttpStatus.OK,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    @GetMapping(
            path = RouteApi.GET_DRIVERS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "name"
    )
    public ResponseEntity<Object> findByDriverName(@RequestParam String name) {
        try {
            List<DriverResponse> result = driverService.getAllDriverByName(name);
            return ResponseHandler.generateResponse(
                    "Success",
                    HttpStatus.OK,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // get All data
    @GetMapping(
            path = RouteApi.GET_DRIVERS,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getAllDrivers() {
        try {
            List<DriverResponse> result = driverService.getAllDrivers();
            return ResponseHandler.generateResponse(
                    "Success",
                    HttpStatus.OK,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // update data
    @PutMapping(
            path = RouteApi.PUT_DRIVER,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> update(@RequestBody DriverRequest request, @PathVariable String driverId) {
        try {
            Driver result = driverService.update(request, driverId);
            return ResponseHandler.generateResponse(
                    "Updated Data Successfully",
                    HttpStatus.OK,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    // delete
    @DeleteMapping(
            path = RouteApi.DELETE_DRIVER
    )
    public ResponseEntity<Object> delete(@PathVariable String driverId) {
        try {
            Driver result = driverService.delete(driverId);
            return ResponseHandler.generateResponse(
                    "Deleted Data Successfully",
                    HttpStatus.OK,
                    result
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }

    }
}
