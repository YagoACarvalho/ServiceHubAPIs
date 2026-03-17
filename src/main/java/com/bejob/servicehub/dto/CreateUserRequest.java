package com.bejob.servicehub.dto;

import com.bejob.servicehub.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "email é obrigatório")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String phone,

        @NotNull(message = "Tipo de usuário é obrigatório")
        UserType type

) {
}
