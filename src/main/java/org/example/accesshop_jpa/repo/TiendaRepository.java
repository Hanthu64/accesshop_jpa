package org.example.accesshop_jpa.repo;

import org.example.accesshop_jpa.domain.Tienda;
import org.example.accesshop_jpa.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TiendaRepository extends JpaRepository<Tienda, Long> {

    @Query(value = "SELECT t FROM Tienda t where t.nombre like %?1%",
            countQuery = "SELECT count(*) FROM Tienda t where t.nombre like %?1%")
    Page<Tienda> buscadorTienda(String nombre, Pageable pageable);
}
