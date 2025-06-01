package com.tuproyecto.almacenapi.repository;

import com.tuproyecto.almacenapi.entity.ItemAlmacen;
import com.tuproyecto.almacenapi.entity.ItemAlmacenKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemAlmacenRepository extends JpaRepository<ItemAlmacen, ItemAlmacenKey> {
}
