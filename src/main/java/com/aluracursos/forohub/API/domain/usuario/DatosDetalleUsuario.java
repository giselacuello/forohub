package com.aluracursos.forohub.API.domain.usuario;

public record DatosDetalleUsuario(Long id, String nombre, String email) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
