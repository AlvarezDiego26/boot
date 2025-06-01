package com.tuproyecto.almacenapi.controller;

import com.tuproyecto.almacenapi.dto.ItemDto;
import com.tuproyecto.almacenapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDto> getAll() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getById(@PathVariable Long id) {
        ItemDto dto = itemService.getItemById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ItemDto> create(@RequestBody ItemDto dto) {
        ItemDto created = itemService.createItem(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(@PathVariable Long id, @RequestBody ItemDto dto) {
        ItemDto updated = itemService.updateItem(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
