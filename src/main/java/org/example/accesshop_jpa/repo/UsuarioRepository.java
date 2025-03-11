package org.example.accesshop_jpa.repo;

import org.example.accesshop_jpa.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT u FROM Usuario u where u.nombre like %?1%",
    countQuery = "SELECT count(*) FROM Usuario u where u.nombre like %?1%")
    Page<Usuario> buscadorUsuarios(String nombre, Pageable pageable);
}
