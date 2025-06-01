package com.tuproyecto.almacenapi.repository;

import com.tuproyecto.almacenapi.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
