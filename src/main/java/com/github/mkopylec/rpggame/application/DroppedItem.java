package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.rpggame.domain.items.HealingPotion;
import com.github.mkopylec.rpggame.domain.items.Item;
import com.github.mkopylec.rpggame.domain.items.Sword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.github.mkopylec.rpggame.application.DroppedItemType.HEALING_POTION;
import static com.github.mkopylec.rpggame.application.DroppedItemType.SWORD;
import static java.util.Collections.unmodifiableList;

public class DroppedItem {

    private static final String HIT_POINTS_BONUS_STAT = "hitPointsBonus";
    private static final String DAMAGE_STAT = "damage";

    private final UUID id;
    private final DroppedItemType itemType;
    private final Map<String, Object> stats = new HashMap<>();

    private DroppedItem(UUID id, DroppedItemType itemType) {
        this.id = id;
        this.itemType = itemType;
    }

    private void addStat(String name, Object value) {
        stats.put(name, value);
    }

    static List<DroppedItem> fromItems(Collection<Item> items) {
        List<DroppedItem> droppedItems = new ArrayList<>(items.size());
        for (Item item : items) {
            DroppedItem droppedItem;
            if (item instanceof HealingPotion) {
                droppedItem = new DroppedItem(item.getId(), HEALING_POTION);
                droppedItem.addStat(HIT_POINTS_BONUS_STAT, ((HealingPotion) item).getHitPointsBonus());
            } else {
                droppedItem = new DroppedItem(item.getId(), SWORD);
                droppedItem.addStat(DAMAGE_STAT, ((Sword) item).getDamage());
            }
            droppedItems.add(droppedItem);
        }
        return unmodifiableList(droppedItems);
    }
}
