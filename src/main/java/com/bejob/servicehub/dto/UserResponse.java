package com.bejob.servicehub.dto;

import com.bejob.servicehub.enums.UserType;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(

        UUID id,
        String name,
        String email,
        String phone,
        UserType type,
        Boolean active,
        LocalDateTime createdAt

) {
}
