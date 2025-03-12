package org.example.accesshop_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.accesshop_jpa.domain.Categoria;
import org.example.accesshop_jpa.domain.Producto;
import org.example.accesshop_jpa.domain.Tienda;
import org.example.accesshop_jpa.dto.ProductoDTO;
import org.example.accesshop_jpa.service.CategoriaService;
import org.example.accesshop_jpa.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    //GET
    @GetMapping(value = {"", "/"}, params = {"buscar-producto"})
    public Page<Producto> buscadorProducto(@RequestParam("buscar-producto") String buscarPorNombre
            , Pageable pageable){
        log.info("Resultados de " + buscarPorNombre);
        return this.productoService.buscadorProducto(Optional.of(buscarPorNombre), pageable);
    }

    @GetMapping({"", "/"})
    public List<Producto> all(){
        return productoService.all();
    }

    @GetMapping("/{id}")
    public Producto one(@PathVariable("id") Long id){
        return productoService.one(id);
    }

    //POST
    @PostMapping({"","/"})
    public Producto newProducto(@RequestBody ProductoDTO productoDTO) {
        Categoria categoria = categoriaService.one(productoDTO.getCategoriaId());

        Producto producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .valoracion(productoDTO.getValoracion())
                .categoria(categoria)
                .build();

        log.info("Creando un producto = " + producto);
        return this.productoService.save(producto);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Producto replaceProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        log.info("Actualizar producto con id = " + id + "\n producto = " + producto);
        return this.productoService.replace(id, producto);
    }

    //DELETE
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable("id") Long id) {
        this.productoService.delete(id);
    }
}
