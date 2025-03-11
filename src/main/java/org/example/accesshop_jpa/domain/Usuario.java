package org.example.accesshop_jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String nombre;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String contrasena;

    @ManyToMany
    @Builder.Default
    @Column(name = "preferencia_categoria")
    @JsonIgnore
    private Set<Categoria> preferenciaCategoria = new HashSet<>();

    @ManyToMany
    @Builder.Default
    @Column(name = "preferencia_tienda")
    @JsonIgnore
    private Set<Categoria> preferenciaTienda = new HashSet<>();
}
