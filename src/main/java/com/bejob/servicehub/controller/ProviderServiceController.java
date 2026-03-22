package com.bejob.servicehub.controller;

import com.bejob.servicehub.dto.AssignServiceToProviderRequest;
import com.bejob.servicehub.dto.ProviderServiceResponse;
import com.bejob.servicehub.service.ProviderServiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider-services")
public class ProviderServiceController {

    private final ProviderServiceService service;

    public ProviderServiceController(ProviderServiceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProviderServiceResponse> assign(@RequestBody @Valid AssignServiceToProviderRequest request){
        ProviderServiceResponse response = service.assign(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
