package com.github.mkopylec.rpggame.application;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DroppedItem {

    private final UUID id;
    private final DroppedItemType itemType;
    private final Map<String, Object> stats = new HashMap<>();

    DroppedItem(UUID id, DroppedItemType itemType) {
        this.id = id;
        this.itemType = itemType;
    }

    void addStat(String name, Object value) {
        stats.put(name, value);
    }
}
