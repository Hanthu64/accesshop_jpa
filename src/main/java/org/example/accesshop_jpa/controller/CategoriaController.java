package org.example.accesshop_jpa.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.accesshop_jpa.domain.Categoria;
import org.example.accesshop_jpa.domain.Producto;
import org.example.accesshop_jpa.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    //GET
    @GetMapping(value = {"", "/"}, params = {"buscar-categoria"})
    public Page<Categoria> buscadorCategorias(@RequestParam("buscar-categoria") String buscarPorNombre
            , Pageable pageable){
        log.info("Resultados de " + buscarPorNombre);
        return this.categoriaService.buscadorCategorias(Optional.of(buscarPorNombre), pageable);
    }

    @GetMapping({"", "/"})
    public List<Categoria> all(){
        return categoriaService.all();
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id){
        return categoriaService.one(id);
    }

    //POST
    @PostMapping({"","/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        log.info("Creando una categoria = " + categoria);
        return this.categoriaService.save(categoria);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        log.info("Actualizar categoria con id = " + id + "\n categoria = " + categoria);
        return this.categoriaService.replace(id, categoria);
    }

    //DELETE
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }
}