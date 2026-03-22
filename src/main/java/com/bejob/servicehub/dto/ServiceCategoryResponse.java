package com.bejob.servicehub.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ServiceCategoryResponse(

        UUID id,
        String name,
        String description,
        Boolean active,
        LocalDateTime createdAt

) {
}
