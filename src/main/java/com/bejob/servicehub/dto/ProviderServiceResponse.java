package com.bejob.servicehub.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProviderServiceResponse(

        UUID id,
        UUID providerId,
        String providerName,
        UUID serviceCategoryId,
        String serviceCategoryName,
        LocalDateTime createdAt

) {
}
