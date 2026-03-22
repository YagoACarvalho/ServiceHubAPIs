package com.bejob.servicehub.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateServiceCategoryRequest(

        @NotBlank(message = "Nome da category")
        String name,

        String description

) {
}
