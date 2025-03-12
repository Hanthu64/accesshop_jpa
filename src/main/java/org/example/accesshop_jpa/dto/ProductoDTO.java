package org.example.accesshop_jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductoDTO {
    private String nombre;
    private int valoracion;

    @JsonProperty("categoria_id")
    private Long categoriaId;
}
