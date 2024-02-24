package com.enigma.enijek.controller;

import com.enigma.enijek.entity.Driver;
import com.enigma.enijek.model.request.DriverRequest;
import com.enigma.enijek.model.response.DriverResponse;
import com.enigma.enijek.model.response.WebResponse;
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
            path = "/api/drivers/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> create(@RequestBody DriverRequest request) {
        driverService.create(request);
        WebResponse<String> response = WebResponse.<String>builder().data("New Driver Added Successfully").build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // find by id
    @GetMapping(
            path = "/api/drivers/{driverId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<DriverResponse>> get(@PathVariable String driverId) {
        DriverResponse response = driverService.get(driverId);
        WebResponse<DriverResponse> webResponse = WebResponse.<DriverResponse>builder().data(response).build();
        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }

    // get All data
    @GetMapping(
            path = "/api/drivers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    public ResponseEntity<WebResponse<List<Driver>>> getAllDrivers() {
//        List<Driver> responseDrivers = driverService.getAllDrivers();
//        return new ResponseEntity<>(WebResponse.<List<Driver>>builder().data(responseDrivers).build(), HttpStatus.OK);
//    }

    public ResponseEntity<WebResponse<List<DriverResponse>>> getAllDrivers() {
        List<DriverResponse> driverResponse = driverService.getAllDrivers();

        return new ResponseEntity<>(WebResponse.<List<DriverResponse>>builder().data(driverResponse).build(),HttpStatus.OK);
    }

    // update data
    @PutMapping(
            path = "/api/drivers/{driverId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> update(@RequestBody DriverRequest request, @PathVariable String driverId) {
        driverService.update(request, driverId);
        WebResponse<String> webResponse = WebResponse.<String>builder().data("Updated Data Successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }

    // delete
    @DeleteMapping(
            path = "/api/drivers/{driverId}"
    )
    public ResponseEntity<WebResponse<String>> delete(@PathVariable String driverId) {
        driverService.delete(driverId);
        WebResponse<String> webResponse = WebResponse.<String>builder().data("Deleted Data Successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }
}
