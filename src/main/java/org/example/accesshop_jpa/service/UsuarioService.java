package org.example.accesshop_jpa.service;

import org.example.accesshop_jpa.domain.Usuario;
import org.example.accesshop_jpa.exception.EntidadNoEncontradaException;
import org.example.accesshop_jpa.repo.CategoriaRepository;
import org.example.accesshop_jpa.repo.TiendaRepository;
import org.example.accesshop_jpa.repo.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    public Page<Usuario> buscadorUsuarios(Optional<String> nombreABuscar, Pageable pageable){
        return this.usuarioRepository.buscadorUsuarios(nombreABuscar.orElse(""), pageable);
    }
    public List<Usuario> all(){return this.usuarioRepository.findAll();}

    public Usuario save(Usuario usuario){
        this.usuarioRepository.save(usuario);
        usuario.getPreferenciaTienda().forEach(p -> {
            this.tiendaRepository.findById(p.getId());
        });
        usuario.getPreferenciaCategoria().forEach(p -> {
            this.categoriaRepository.findById(p.getId());
        });
        return usuario;
    }

    public Usuario one(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(id, Usuario.class));
    }

    public Usuario replace(Long id, Usuario usuario) {

        return this.usuarioRepository.findById(id).map( i -> {
                    if (id.equals(usuario.getId())) return this.usuarioRepository.save(usuario);
                    else throw new EntidadNoEncontradaException(id, Usuario.class);
                }
        ).orElseThrow(() -> new EntidadNoEncontradaException(id, Usuario.class));

    }

    public void delete(Long id) {
        this.usuarioRepository.findById(id).map(t -> {this.usuarioRepository.delete(t);
                    return t;})
                .orElseThrow(() -> new EntidadNoEncontradaException(id, Usuario.class));
    }
}
