package com.tuproyecto.almacenapi.service;

import com.tuproyecto.almacenapi.dto.AlmacenDto;
import com.tuproyecto.almacenapi.entity.Almacen;
import com.tuproyecto.almacenapi.repository.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlmacenService {
    @Autowired
    private AlmacenRepository almacenRepository;

    public List<AlmacenDto> getAllAlmacenes() {
        return almacenRepository.findAll().stream()
                .map(a -> {
                    AlmacenDto dto = new AlmacenDto();
                    dto.setId(a.getId());
                    dto.setUbicacion(a.getUbicacion());
                    return dto;
                }).collect(Collectors.toList());
    }

    public AlmacenDto getAlmacenById(Long id) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        AlmacenDto dto = new AlmacenDto();
        dto.setId(almacen.getId());
        dto.setUbicacion(almacen.getUbicacion());
        return dto;
    }

    public AlmacenDto createAlmacen(AlmacenDto almacenDto) {
        Almacen almacen = new Almacen();
        almacen.setUbicacion(almacenDto.getUbicacion());
        Almacen saved = almacenRepository.save(almacen);
        almacenDto.setId(saved.getId());
        return almacenDto;
    }

    public AlmacenDto updateAlmacen(Long id, AlmacenDto almacenDto) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        almacen.setUbicacion(almacenDto.getUbicacion());
        Almacen updated = almacenRepository.save(almacen);
        almacenDto.setId(updated.getId());
        return almacenDto;
    }

    public void deleteAlmacen(Long id) {
        almacenRepository.deleteById(id);
    }
}
