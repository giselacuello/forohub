package com.aluracursos.forohub.API.domain.topico;

import com.aluracursos.forohub.API.domain.curso.Curso;
import com.aluracursos.forohub.API.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosListaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        String autor,
        String curso
) {
    public DatosListaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
