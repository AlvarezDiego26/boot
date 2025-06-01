package com.tuproyecto.almacenapi.controller;

import com.tuproyecto.almacenapi.dto.AlmacenDto;
import com.tuproyecto.almacenapi.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
public class AlmacenController {
    @Autowired
    private AlmacenService almacenService;

    @GetMapping
    public List<AlmacenDto> getAll() {
        return almacenService.getAllAlmacenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenDto> getById(@PathVariable Long id) {
        AlmacenDto dto = almacenService.getAlmacenById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AlmacenDto> create(@RequestBody AlmacenDto dto) {
        AlmacenDto created = almacenService.createAlmacen(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenDto> update(@PathVariable Long id, @RequestBody AlmacenDto dto) {
        AlmacenDto updated = almacenService.updateAlmacen(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        almacenService.deleteAlmacen(id);
        return ResponseEntity.noContent().build();
    }
}
