package com.enigma.enijek.controller;

import com.enigma.enijek.entity.Driver;
import com.enigma.enijek.model.request.DriverRequest;
import com.enigma.enijek.model.response.DriverResponse;
import com.enigma.enijek.model.response.WebResponse;
import com.enigma.enijek.routes.RouteApi;
import com.enigma.enijek.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<WebResponse<String>> create(@RequestBody DriverRequest request) {
        driverService.create(request);
        return new ResponseEntity<>(
                WebResponse.<String>builder().data("New Driver Added Successfully").build(),
                HttpStatus.CREATED
        );
    }

    // find by id
    @GetMapping(
            path = RouteApi.GET_DRIVER,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<DriverResponse>> get(@PathVariable String driverId) {
        DriverResponse response = driverService.get(driverId);
        return new ResponseEntity<>(
                WebResponse.<DriverResponse>builder().data(response).build(),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = RouteApi.GET_DRIVERS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "name"
    )
    public ResponseEntity<WebResponse<List<DriverResponse>>> findByDriverName(@RequestParam String name){
        List<DriverResponse> driverResponses = driverService.getAllDriverByName(name);
        return new ResponseEntity<>(
                WebResponse.<List<DriverResponse>>builder()
                        .data(driverResponses)
                        .build(),
                HttpStatus.OK
        );
    }

    // get All data
    @GetMapping(
            path = RouteApi.GET_DRIVERS,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<DriverResponse>>> getAllDrivers() {
        List<DriverResponse> driverResponse = driverService.getAllDrivers();
        return new ResponseEntity<>(
                WebResponse.<List<DriverResponse>>builder().data(driverResponse).build()
                , HttpStatus.OK
        );
    }

    // update data
    @PutMapping(
            path = RouteApi.PUT_DRIVER,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> update(@RequestBody DriverRequest request, @PathVariable String driverId) {
        driverService.update(request, driverId);
        return new ResponseEntity<>(
                WebResponse.<String>builder().data("Updated Data Successfully").build(),
                HttpStatus.OK
        );
    }

    // delete
    @DeleteMapping(
            path = RouteApi.DELETE_DRIVER
    )
    public ResponseEntity<WebResponse<String>> delete(@PathVariable String driverId) {
        driverService.delete(driverId);
        return new ResponseEntity<>(
                WebResponse.<String>builder().data("Deleted Data Successfully").build(),
                HttpStatus.OK
        );

    }
}
