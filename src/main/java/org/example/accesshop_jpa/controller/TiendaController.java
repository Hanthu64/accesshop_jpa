package org.example.accesshop_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.accesshop_jpa.domain.Producto;
import org.example.accesshop_jpa.domain.Tienda;
import org.example.accesshop_jpa.domain.Usuario;
import org.example.accesshop_jpa.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/api/tiendas")
public class TiendaController {
    @Autowired
    private TiendaService tiendaService;

    //GET
    @GetMapping(value = {"", "/"}, params = {"buscar-tienda"})
    public Page<Tienda> buscadorTienda(@RequestParam("buscar-tienda") String buscarPorNombre
            , Pageable pageable){
        log.info("Resultados de " + buscarPorNombre);
        return this.tiendaService.buscadorTienda(Optional.of(buscarPorNombre), pageable);
    }

    @GetMapping({"", "/"})
    public List<Tienda> all(){
        return tiendaService.all();
    }

    @GetMapping("/{id}")
    public Tienda one(@PathVariable("id") Long id){
        return tiendaService.one(id);
    }

    //POST
    @PostMapping({"","/"})
    public Tienda newTienda(@RequestBody Tienda tienda) {
        log.info("Creando un tienda = " + tienda);
        return this.tiendaService.save(tienda);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Tienda replaceTienda(@PathVariable("id") Long id, @RequestBody Tienda tienda) {
        log.info("Actualizar tienda con id = " + id + "\n tienda = " + tienda);
        return this.tiendaService.replace(id, tienda);
    }

    //DELETE
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTienda(@PathVariable("id") Long id) {
        this.tiendaService.delete(id);
    }
}
