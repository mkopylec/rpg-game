package com.github.mkopylec.rpggame.domain.items;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;

@Entity
@AggregateRoot
public class HealingPotion extends Item {

    static final int MIN_POTION_HP_BONUS = 10;
    static final int MAX_POTION_HP_BONUS = 50;

    private final int hitPointsBonus;

    HealingPotion(int hitPointsBonus) {
        this.hitPointsBonus = hitPointsBonus;
    }

    public int getHitPointsBonus() {
        return hitPointsBonus;
    }
}
