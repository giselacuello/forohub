package com.aluracursos.forohub.API.controller;

import com.aluracursos.forohub.API.domain.usuario.DatosDetalleUsuario;
import com.aluracursos.forohub.API.domain.usuario.DatosRegistroUsuario;
import com.aluracursos.forohub.API.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.API.domain.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DatosDetalleUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos) {
        var usuario = usuarioService.registrarUsuario(datos);
        return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<DatosDetalleUsuario>> listar() {
        var lista = usuarioService.listarUsuarios();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario((id));
        return ResponseEntity.noContent().build();
    }
}
