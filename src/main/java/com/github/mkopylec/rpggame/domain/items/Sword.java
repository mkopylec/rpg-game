package com.github.mkopylec.rpggame.domain.items;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;

@Entity
@AggregateRoot
public class Sword extends Item {

    private final int damage;

    public Sword(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
