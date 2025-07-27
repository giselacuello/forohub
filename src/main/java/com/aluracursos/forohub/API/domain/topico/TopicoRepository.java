package com.aluracursos.forohub.API.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(@NotBlank String titulo, @NotBlank String mensaje);

    @Query("""
            SELECT t FROM Topico t
            WHERE t.curso.nombre = :curso
            AND FUNCTION('YEAR', t.fechaCreacion) = :anio            
            """)
    List<Topico> findByCursoNombreAndAnio(@Param("curso") String curso, @Param("anio") int anio);

}
