package com.esri.account.controller;

import com.esri.account.bean.Api;
import com.esri.account.services.ApiService;
import com.esri.account.services.ValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApiController {

    private ApiService apiService;
    private ValidationErrorService validationErrorService;

    public ApiController(ApiService apiService, ValidationErrorService validationErrorService) {
        this.apiService = apiService;
        this.validationErrorService = validationErrorService;
    }

    @PostMapping("/newApiKey")
    public ResponseEntity<?> addNewApiKey(@Valid @RequestBody Api apiKey, BindingResult result){

        ResponseEntity<?> errorMap = validationErrorService.ValidationService(result);
        if(errorMap!=null) return errorMap;

        Api newApiKey=apiService.addNewApiKey(apiKey);
        return new ResponseEntity<Api>(newApiKey, HttpStatus.CREATED);
    }

    @DeleteMapping("/dApi/{key}")
    public ResponseEntity<?> deleteApiKey(@PathVariable Long key){
        apiService.deleteApiKey(key);
        return new ResponseEntity<String>("API key deleted", HttpStatus.OK);}
}
