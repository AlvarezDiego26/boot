package com.tuproyecto.almacenapi.service;

import com.tuproyecto.almacenapi.dto.ItemDto;
import com.tuproyecto.almacenapi.entity.Item;
import com.tuproyecto.almacenapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> {
                    ItemDto dto = new ItemDto();
                    dto.setId(item.getId());
                    dto.setNombre(item.getNombre());
                    return dto;
                }).collect(Collectors.toList());
    }

    public ItemDto getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item no encontrado"));
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setNombre(item.getNombre());
        return dto;
    }

    public ItemDto createItem(ItemDto itemDto) {
        Item item = new Item();
        item.setNombre(itemDto.getNombre());
        Item saved = itemRepository.save(item);
        itemDto.setId(saved.getId());
        return itemDto;
    }

    public ItemDto updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item no encontrado"));
        item.setNombre(itemDto.getNombre());
        Item updated = itemRepository.save(item);
        itemDto.setId(updated.getId());
        return itemDto;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
