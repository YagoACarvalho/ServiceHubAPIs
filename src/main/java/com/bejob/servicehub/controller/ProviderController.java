package com.bejob.servicehub.controller;

import com.bejob.servicehub.dto.CreateProviderRequest;
import com.bejob.servicehub.dto.ProviderResponse;
import com.bejob.servicehub.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public ResponseEntity<ProviderResponse> create(@RequestBody @Valid CreateProviderRequest request){
        ProviderResponse response = providerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
