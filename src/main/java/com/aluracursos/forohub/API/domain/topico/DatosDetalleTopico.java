package com.aluracursos.forohub.API.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fechaCreacion,
        String status
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),      // Asumiendo que Usuario tiene getNombre()
                topico.getCurso().getNombre(),      // Asumiendo que Curso tiene getNombre()
                topico.getFechaCreacion(),
                topico.getStatus().name()           // Convertimos el enum a String
        );
    }
}
