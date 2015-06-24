package com.github.mkopylec.rpggame.domain.items;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;

@Entity
@AggregateRoot
public class Sword extends Item {

    static final int MIN_SWORD_DAMAGE = 1;
    static final int MAX_SWORD_DAMAGE = 10;

    private final int damage;

    Sword(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
