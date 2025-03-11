package org.example.accesshop_jpa.repo;

import org.example.accesshop_jpa.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(value = "SELECT c FROM Categoria c where c.nombre like %?1%",
            countQuery = "SELECT count(*) FROM Categoria c where c.nombre like %?1%")
    Page<Categoria> buscadorCategorias(String nombre, Pageable pageable);
}
