package com.commercetools.ct_core_api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")

public class GenericJsonController {

    private final ObjectMapper objectMapper;

    @Autowired
    private CommerceToolsApiController API;

    public GenericJsonController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/json")
    public ResponseEntity<Object> handleJson(@RequestBody Map<String, Object> json) {
        // If JSON is valid, it will be automatically mapped to the Map
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException e) {
        // Handle the JSON parsing error
        return new ResponseEntity<>("Invalid JSON syntax", HttpStatus.BAD_REQUEST);
    }

}
