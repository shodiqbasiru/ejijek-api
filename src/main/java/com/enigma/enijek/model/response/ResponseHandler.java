package com.enigma.enijek.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responObject){
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status);
        map.put("data",responObject);

        return new ResponseEntity<>(map, status);
    }
}
