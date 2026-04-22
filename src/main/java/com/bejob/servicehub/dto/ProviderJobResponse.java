package com.bejob.servicehub.dto;

import com.bejob.servicehub.enums.JobStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProviderJobResponse(
        UUID jobId,
        String description,
        String city,
        String address,
        JobStatus status,
        UUID serviceCategoryId,
        String serviceCategoryName,
        UUID clientId,
        String clientName,
        UUID providerId,
        String providerName,
        LocalDateTime createdAt
) {}