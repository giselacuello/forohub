package com.aluracursos.forohub.API.controller;

import com.aluracursos.forohub.API.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService registroTopico;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        var detalleTopico = registroTopico.registrar(datos);
        return ResponseEntity.ok(detalleTopico);
    }

    //Opción obligatoria
    @GetMapping
    public ResponseEntity<List<DatosListaTopico>> listar() {
        var lista = topicoRepository.findAll().stream()
                .map(DatosListaTopico::new)
                .toList();

        return ResponseEntity.ok(lista);
    }

    //Opcion 1 Primeros 10 ordenados por fecha ASC
    @GetMapping("/ordenados")
    public ResponseEntity<List<DatosListaTopico>> listaOrdenados() {
        Pageable paginacion = PageRequest.of(0, 10, Sort.by("fechaCreacion").ascending());
        var lista = topicoRepository.findAll(paginacion)
                .stream()
                .map(DatosListaTopico::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    //Opcion 2 Filtro por curso y año
    @GetMapping("/filtrar/{curso}/{anio}")
    public ResponseEntity<List<DatosListaTopico>> filtrarPorCursoYAnio(@PathVariable String curso, @PathVariable int anio) {

        var lista = topicoRepository.findByCursoNombreAndAnio(curso, anio)
                .stream()
                .map(DatosListaTopico::new)
                .toList();

        return ResponseEntity.ok(lista);
    }

    //detalle de topico por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerPorId(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);

        if(topico.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleTopico(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {

        var topicoActualizado = registroTopico.actualizar(id, datos);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        registroTopico.eliminar(id);
        return ResponseEntity.noContent().build(); //HTTP204
    }



}
