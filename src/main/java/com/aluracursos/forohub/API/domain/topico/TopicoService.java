package com.aluracursos.forohub.API.domain.topico;

import com.aluracursos.forohub.API.domain.ValidationException;
import com.aluracursos.forohub.API.domain.curso.Curso;
import com.aluracursos.forohub.API.domain.curso.CursoRepository;
import com.aluracursos.forohub.API.domain.usuario.Usuario;
import com.aluracursos.forohub.API.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

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

    //@Transactional lo dejé en el controller como en el curso
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

    public DatosDetalleTopico actualizar(Long id, DatosActualizarTopico datos) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        //if (optionalTopico.isPresent()) {
        //        Topico topico = optionalTopico.get();
        //        topico.setTitulo(datos.titulo());
        //        topico.setMensaje(datos.mensaje());
        //        return new DatosDetalleTopico(topico);

        if(optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.actualizarTopico(datos);
            return new DatosDetalleTopico(topico);
        } else {
            throw new ValidationException("Tópico no encontrado");
        }
    }

    public void eliminar(Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isPresent()) {
            topicoRepository.delete(optionalTopico.get());
        } else {
            throw new ValidationException("Topico no encontrado");
        }
    }
}
