package com.github.mkopylec.rpggame.domain.items;

import com.github.mkopylec.ddd.buildingblocks.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository {

    void save(Item item);

    HealingPotion findOneHealingPotion(UUID potionId);

    Sword findOneSword(UUID swordId);

    void delete(Item item);
}
