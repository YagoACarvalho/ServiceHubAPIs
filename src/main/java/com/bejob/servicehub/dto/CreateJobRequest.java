package com.bejob.servicehub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateJobRequest(

        @NotNull(message = "ClientId é obrigatório")
        UUID id,

        @NotNull(message = "ServiceCategoryId é obrigatório")
        UUID serviceCategoryId,

        @NotBlank(message = "Descrição é obrigatória")
        String description,

        @NotBlank(message = "Cidade é obrigatória")
        String city,

        @NotBlank(message = "Endereço é obrigatório")
        String address


) {
}
