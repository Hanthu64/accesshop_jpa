package org.example.accesshop_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.accesshop_jpa.domain.Tienda;
import org.example.accesshop_jpa.domain.Usuario;
import org.example.accesshop_jpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    //GET
    @GetMapping(value = {"", "/"}, params = {"buscar-usuario"})
    public Page<Usuario> buscadorUsuarios(@RequestParam("buscar-usuario") String buscarPorNombre
    , Pageable pageable){
        log.info("Resultados de " + buscarPorNombre);
        return this.usuarioService.buscadorUsuarios(Optional.of(buscarPorNombre), pageable);
    }

    @GetMapping({"", "/"})
    public List<Usuario> all(){
        return usuarioService.all();
    }

    @GetMapping("/{id}")
    public Usuario one(@PathVariable("id") Long id){
        return usuarioService.one(id);
    }

    //POST
    @PostMapping({"","/"})
    public Usuario newUsuario(@RequestBody Usuario usuario) {
        log.info("Creando un usuario = " + usuario);
        return this.usuarioService.save(usuario);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Usuario replaceUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        log.info("Actualizar usuario con id = " + id + "\n usuario = " + usuario);
        return this.usuarioService.replace(id, usuario);
    }

    //DELETE
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Long id) {
        this.usuarioService.delete(id);
    }
}
