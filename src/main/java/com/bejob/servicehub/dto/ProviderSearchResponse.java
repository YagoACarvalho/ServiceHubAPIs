package com.bejob.servicehub.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProviderSearchResponse(
        UUID providerId,
        UUID userId,
        String providerName,
        String email,
        String phone,
        String city,
        String description,
        Boolean verified,
        Boolean available,
        Double averageRating,
        String serviceCategoryName


) {
}
