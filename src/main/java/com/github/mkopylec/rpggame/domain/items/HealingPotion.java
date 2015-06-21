package com.github.mkopylec.rpggame.domain.items;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;

@Entity
@AggregateRoot
public class HealingPotion extends Item {

    private final int hitPointsBonus;

    public HealingPotion(int hitPointsBonus) {
        this.hitPointsBonus = hitPointsBonus;
    }

    public int getHitPointsBonus() {
        return hitPointsBonus;
    }
}
