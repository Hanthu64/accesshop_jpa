package org.example.accesshop_jpa.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.accesshop_jpa.domain.Categoria;
import org.example.accesshop_jpa.domain.Tienda;
import org.example.accesshop_jpa.domain.Usuario;
import org.example.accesshop_jpa.exception.EntidadNoEncontradaException;
import org.example.accesshop_jpa.repo.TiendaRepository;
import org.example.accesshop_jpa.repo.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiendaService {
    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRepository productoRepository;

    public Page<Tienda> buscadorTienda(Optional<String> nombreABuscar, Pageable pageable){
        return this.tiendaRepository.buscadorTienda(nombreABuscar.orElse(""), pageable);
    }

    public List<Tienda> all(){return this.tiendaRepository.findAll();}

    public Tienda save(Tienda tienda){
        this.tiendaRepository.save(tienda);
        tienda.getUsuariosPreferentes().forEach(p -> {
            this.usuarioRepository.findById(p.getId());
        });
        tienda.getTiendaProducto().forEach(p -> {
            this.productoRepository.findById(p.getId());
        });
        return tienda;
    }

    public Tienda one(Long id) {
        return this.tiendaRepository.findById(id)
                .orElseThrow(() ->  new EntidadNoEncontradaException(id, Tienda.class));
    }

    public Tienda replace(Long id, Tienda tienda) {

        return this.tiendaRepository.findById(id).map(i -> {
                    if (id.equals(tienda.getId())) return this.tiendaRepository.save(tienda);
                    else throw new EntidadNoEncontradaException(id, Tienda.class);
                }
        ).orElseThrow(() -> new EntidadNoEncontradaException(id, Tienda.class));

    }

    public void delete(Long id) {
        this.tiendaRepository.findById(id).map(t -> {this.tiendaRepository.delete(t);
                    return t;})
                .orElseThrow(() -> new EntidadNoEncontradaException(id, Tienda.class));
    }
}
