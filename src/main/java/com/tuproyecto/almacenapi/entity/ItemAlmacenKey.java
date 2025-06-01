package com.tuproyecto.almacenapi.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemAlmacenKey implements Serializable {

    private Long itemId;

    private Long almacenId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ItemAlmacenKey))
            return false;
        ItemAlmacenKey that = (ItemAlmacenKey) o;
        return Objects.equals(getItemId(), that.getItemId()) &&
                Objects.equals(getAlmacenId(), that.getAlmacenId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getAlmacenId());
    }
}
