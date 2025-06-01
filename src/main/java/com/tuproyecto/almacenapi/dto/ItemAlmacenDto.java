package com.tuproyecto.almacenapi.dto;

import lombok.Data;

@Data
public class ItemAlmacenDto {
    private Long itemId;
    private Long almacenId;
    private Integer cantidad;
}
