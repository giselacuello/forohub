package com.aluracursos.forohub.API.controller;

import com.aluracursos.forohub.API.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.API.domain.topico.RegistroDeTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private RegistroDeTopico registroTopico;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        var detalleTopico = registroTopico.registrar(datos);
        return ResponseEntity.ok(detalleTopico);
    }

}
