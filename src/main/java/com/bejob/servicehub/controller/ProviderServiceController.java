package com.bejob.servicehub.controller;

import com.bejob.servicehub.dto.AssignServiceToProviderRequest;
import com.bejob.servicehub.dto.ProviderSearchResponse;
import com.bejob.servicehub.dto.ProviderServiceResponse;
import com.bejob.servicehub.service.ProviderServiceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<List<ProviderSearchResponse>> search(
            @RequestParam String category,
            @RequestParam String city
    ) {
        List<ProviderSearchResponse> response = service.search(category, city);
        return ResponseEntity.ok(response);
    }
}
