package com.github.mkopylec.rpggame.domain.hero;

import com.github.mkopylec.ddd.buildingblocks.ValueObject;
import com.github.mkopylec.rpggame.domain.items.Item;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.collections4.CollectionUtils.find;

@ValueObject
class Backpack {

    @DBRef(lazy = true)
    private final List<Item> items = new ArrayList<>();

    void putItem(Item item) {
        items.add(checkNotNull(item, "Item not provided"));
    }

    Item getItem(UUID itemId) {
        checkNotNull(itemId, "Item id not provided");
        Item item = find(items, it -> it.getId().equals(itemId));
        checkNotNull(item, "Backpack does not contain an item with id: %s", itemId);
        items.remove(item);

        return item;
    }
}
