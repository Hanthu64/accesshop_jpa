package org.example.accesshop_jpa.repo;

import org.example.accesshop_jpa.domain.Producto;
import org.example.accesshop_jpa.domain.Tienda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "SELECT p FROM Producto p WHERE p.nombre LIKE %?1%",
            countQuery = "SELECT count(*) FROM Producto p WHERE p.nombre LIKE %?1%")
    Page<Producto> buscadorProducto(String nombre, Pageable pageable);
}
