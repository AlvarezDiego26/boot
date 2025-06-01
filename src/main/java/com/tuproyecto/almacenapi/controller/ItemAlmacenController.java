package com.tuproyecto.almacenapi.controller;

import com.tuproyecto.almacenapi.dto.ItemAlmacenDto;
import com.tuproyecto.almacenapi.service.ItemAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-almacenes")
public class ItemAlmacenController {
    @Autowired
    private ItemAlmacenService itemAlmacenService;

    @GetMapping
    public List<ItemAlmacenDto> getAll() {
        return itemAlmacenService.getAll();
    }

    @PostMapping
    public ResponseEntity<ItemAlmacenDto> create(@RequestBody ItemAlmacenDto dto) {
        ItemAlmacenDto created = itemAlmacenService.create(dto);
        return ResponseEntity.ok(created);
    }
}
