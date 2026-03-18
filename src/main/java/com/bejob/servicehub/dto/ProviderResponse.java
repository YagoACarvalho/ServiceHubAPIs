package com.bejob.servicehub.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProviderResponse(

        UUID id,
        UUID userId,
        String userName,
        String email,
        String phone,
        String document,
        String city,
        String description,
        Boolean verified,
        Boolean available,
        Double averageRating,
        LocalDateTime createdAt

) {
}
