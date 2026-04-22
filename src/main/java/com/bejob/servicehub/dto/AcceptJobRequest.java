package com.bejob.servicehub.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AcceptJobRequest(

        @NotNull(message = "JobId é obrigatório")
        UUID jobId,

        @NotNull(message = "ProviderId é obrigatório")
        UUID providerId
) {
}
