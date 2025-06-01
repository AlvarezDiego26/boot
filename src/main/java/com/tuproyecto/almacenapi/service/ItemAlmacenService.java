package com.tuproyecto.almacenapi.service;

import com.tuproyecto.almacenapi.dto.ItemAlmacenDto;
import com.tuproyecto.almacenapi.entity.*;
import com.tuproyecto.almacenapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemAlmacenService {
    @Autowired
    private ItemAlmacenRepository itemAlmacenRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<ItemAlmacenDto> getAll() {
        return itemAlmacenRepository.findAll().stream()
                .map(ia -> {
                    ItemAlmacenDto dto = new ItemAlmacenDto();
                    dto.setItemId(ia.getItem().getId());
                    dto.setAlmacenId(ia.getAlmacen().getId());
                    dto.setCantidad(ia.getCantidad());
                    return dto;
                }).collect(Collectors.toList());
    }

    public ItemAlmacenDto create(ItemAlmacenDto dto) {
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        Almacen almacen = almacenRepository.findById(dto.getAlmacenId())
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));

        ItemAlmacen ia = new ItemAlmacen();
        ia.setItem(item);
        ia.setAlmacen(almacen);
        ia.setCantidad(dto.getCantidad());

        // Clave compuesta
        ItemAlmacenKey key = new ItemAlmacenKey(dto.getItemId(), dto.getAlmacenId());
        ia.setId(key);

        ItemAlmacen saved = itemAlmacenRepository.save(ia);
        dto.setCantidad(saved.getCantidad());
        return dto;
    }

    // Opcional: actualizar cantidad, eliminar relación etc.
}
