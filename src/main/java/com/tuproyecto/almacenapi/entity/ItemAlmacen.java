package com.tuproyecto.almacenapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_almacen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemAlmacen {

    @EmbeddedId
    private ItemAlmacenKey id = new ItemAlmacenKey();

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("almacenId")
    @JoinColumn(name = "almacen_id")
    private Almacen almacen;

    private Integer cantidad;
}
