package com.bejob.servicehub.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignServiceToProviderRequest(

        @NotNull(message = "ProviderId é obrigatório")
        UUID providerId,

        @NotNull(message = "ServiceCategoryId é obrigatório")
        UUID serviceCategoryId
) {
}
