package org.example.accesshop_jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tienda_id")
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String nombre;

    @ManyToMany
    @Builder.Default
    @Column(name = "tienda_producto")
    @JsonIgnore
    private Set<Producto> tiendaProducto = new HashSet<>();
}
