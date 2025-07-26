package com.aluracursos.forohub.API.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long idAutor,
        @NotNull Long idCurso
        ) {
}
