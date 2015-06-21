package com.github.mkopylec.rpggame.domain.items;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public abstract class Item {

    @Id
    private final UUID id = randomUUID();//Entity ID

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        return new EqualsBuilder()
                .append(id, item.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
