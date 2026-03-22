package com.bejob.servicehub.controller;

import com.bejob.servicehub.dto.CreateServiceCategoryRequest;
import com.bejob.servicehub.dto.ServiceCategoryResponse;
import com.bejob.servicehub.service.ServiceCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-categories")
public class ServiceCategoryController {

    private final ServiceCategoryService service;

    public ServiceCategoryController(ServiceCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceCategoryResponse> create(@RequestBody @Valid CreateServiceCategoryRequest request){
        ServiceCategoryResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
