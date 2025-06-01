package com.tuproyecto.almacenapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "almacenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ubicacion;

    // Relación con ItemAlmacen
    @OneToMany(mappedBy = "almacen", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemAlmacen> itemAlmacenes = new HashSet<>();
}
