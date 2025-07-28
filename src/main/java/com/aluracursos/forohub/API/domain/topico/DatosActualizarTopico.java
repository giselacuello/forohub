package com.aluracursos.forohub.API.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje
) {
}
