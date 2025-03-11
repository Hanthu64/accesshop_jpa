package org.example.accesshop_jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String nombre;

    @ManyToMany
    @Builder.Default
    public Set<Usuario> usuariosPreferentes = new HashSet<>();

    @OneToMany(mappedBy = "categoria")
    @Builder.Default
    @JsonIgnore
    public Set<Producto> productos = new HashSet<>();
}
