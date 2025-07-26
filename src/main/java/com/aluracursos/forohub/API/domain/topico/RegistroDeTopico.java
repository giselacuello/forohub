package com.aluracursos.forohub.API.domain.topico;

import com.aluracursos.forohub.API.domain.ValidationException;
import com.aluracursos.forohub.API.domain.curso.Curso;
import com.aluracursos.forohub.API.domain.curso.CursoRepository;
import com.aluracursos.forohub.API.domain.usuario.Usuario;
import com.aluracursos.forohub.API.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistroDeTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //creo podria ser con constructor con private final TopicoRepository
//    public RegistroDeTopico(TopicoRepository repository) {
//        this.repository = repository;
//    }

    @Transactional
    public DatosDetalleTopico registrar(@Valid DatosRegistroTopico datos) {
        //verificar duplicados
        boolean existe = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if(existe) {
            throw new ValidationException("Ya existe un tópico con ese titulo y mensaje");
        }

        //Crear
        Usuario autor = usuarioRepository.getReferenceById(datos.idAutor());
        Curso curso = cursoRepository.getReferenceById(datos.idCurso());

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), autor, curso);
        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);
    }
}
