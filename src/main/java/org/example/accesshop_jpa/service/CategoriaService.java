package org.example.accesshop_jpa.service;

import org.example.accesshop_jpa.domain.Categoria;
import org.example.accesshop_jpa.domain.Usuario;
import org.example.accesshop_jpa.exception.EntidadNoEncontradaException;
import org.example.accesshop_jpa.repo.CategoriaRepository;
import org.example.accesshop_jpa.repo.ProductoRepository;
import org.example.accesshop_jpa.repo.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Categoria> buscadorCategorias(Optional<String> nombreABuscar, Pageable pageable){
        return this.categoriaRepository.buscadorCategorias(nombreABuscar.orElse(""), pageable);
    }

    public List<Categoria> all(){return this.categoriaRepository.findAll();}

    public Categoria save(Categoria categoria){
        this.categoriaRepository.save(categoria);
        categoria.getUsuariosPreferentes().forEach(p -> {
            this.usuarioRepository.findById(p.getId());
        });
        categoria.getProductos().forEach(p -> {
            this.categoriaRepository.findById(p.getId());
        });
        return categoria;
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(id, Categoria.class));
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map(i -> {
                    if (id.equals(categoria.getId())) return this.categoriaRepository.save(categoria);
                    else throw new EntidadNoEncontradaException(id, Categoria.class);
                }
        ).orElseThrow(() -> new EntidadNoEncontradaException(id, Categoria.class));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(t -> {this.categoriaRepository.delete(t);
                    return t;})
                .orElseThrow(() -> new EntidadNoEncontradaException(id, Categoria.class));
    }
}
