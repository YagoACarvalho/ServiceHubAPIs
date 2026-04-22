package com.bejob.servicehub.controller;


import com.bejob.servicehub.dto.*;
import com.bejob.servicehub.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponse> create(@RequestBody @Valid CreateJobRequest request) {
        JobResponse response = jobService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/accept")
    public ResponseEntity<JobResponse> accept(@RequestBody @Valid AcceptJobRequest request) {
        JobResponse response = jobService.accept(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/open")
    public ResponseEntity<List<JobSearchResponse>> findOpenJobs(
            @RequestParam String city,
            @RequestParam(required = false) UUID categoryId
    ) {
        List<JobSearchResponse> response = jobService.findOpenJobs(city, categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ProviderJobResponse>> findJobsByProvider(
            @PathVariable UUID providerId
    ) {
        List<ProviderJobResponse> response = jobService.findJobsByProvider(providerId);
        return ResponseEntity.ok(response);
    }
}
