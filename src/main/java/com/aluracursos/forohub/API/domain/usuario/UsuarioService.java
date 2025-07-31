package com.aluracursos.forohub.API.domain.usuario;

import com.aluracursos.forohub.API.domain.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(DatosRegistroUsuario datos) {
        repository.findUsuarioByEmail(datos.email()).ifPresent(u -> {
            throw new IllegalArgumentException("Ya existe un usario con ese email");
        });

        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        Usuario usuario = new Usuario(datos.nombre(), datos.email(), contrasenaEncriptada);

        return repository.save(usuario);
    }

    public List<DatosDetalleUsuario> listarUsuarios() {
        return repository.findAll().stream()
                .map(DatosDetalleUsuario::new)
                .toList();
    }

    public void eliminarUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
    }
}
