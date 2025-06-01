package com.tuproyecto.almacenapi.repository;

import com.tuproyecto.almacenapi.entity.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
