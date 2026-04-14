package com.bejob.servicehub.dto;

import com.bejob.servicehub.enums.JobStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record JobResponse(

        UUID id,
        UUID clientId,
        String clientName,
        UUID serviceCategoryId,
        String serviceCategoryName,
        String description,
        String city,
        String address,
        JobStatus status,
        UUID providerId,
        String providerName,
        LocalDateTime createdAt

) {
}
