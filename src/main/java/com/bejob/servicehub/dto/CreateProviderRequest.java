package com.bejob.servicehub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateProviderRequest(

        @NotNull(message = "UserId é obrigatório")
        UUID userId,

        @NotBlank(message = "Documento é obrigatório")
        String document,

        @NotBlank(message = "Cidade é obrigatória")
        String City,

        String description
        ) {
}
