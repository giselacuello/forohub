package com.aluracursos.forohub.API.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);//Para autenticacion

    Optional<Usuario> findUsuarioByEmail(String email);//Para validación

}
