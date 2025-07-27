package com.aluracursos.forohub.API.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank String email,
        @NotBlank String contrasena
) {
}
