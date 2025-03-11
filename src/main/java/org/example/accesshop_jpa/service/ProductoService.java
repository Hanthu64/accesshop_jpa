package org.example.accesshop_jpa.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.accesshop_jpa.domain.Categoria;
import org.example.accesshop_jpa.domain.Producto;
import org.example.accesshop_jpa.domain.Usuario;
import org.example.accesshop_jpa.exception.EntidadNoEncontradaException;
import org.example.accesshop_jpa.repo.CategoriaRepository;
import org.example.accesshop_jpa.repo.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Page<Producto> buscadorProducto(Optional<String> nombreABuscar, Pageable pageable){
        return this.productoRepository.buscadorProducto(nombreABuscar.orElse(""), pageable);
    }

    public List<Producto> all(){return this.productoRepository.findAll();}
    public Producto save(Producto producto){
        this.productoRepository.save(producto);
        return producto;
    }

    public Producto one(Long id) {
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(id, Producto.class));
    }

    public Producto replace(Long id, Producto producto) {

        return this.productoRepository.findById(id).map(i -> {
                    if (id.equals(producto.getId())) return this.productoRepository.save(producto);
                    else throw new EntidadNoEncontradaException(id, Producto.class);
                }
        ).orElseThrow(() -> new EntidadNoEncontradaException(id, Producto.class));
    }

    public void delete(Long id) {
        this.productoRepository.findById(id).map(t -> {this.productoRepository.delete(t);
                    return t;})
                .orElseThrow(() -> new EntidadNoEncontradaException(id, Producto.class));
    }
}