package com.github.mkopylec.rpggame.domain.characters;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;
import com.github.mkopylec.rpggame.domain.world.Location;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
@AggregateRoot
public class Enemy extends Character {

    static final int MIN_ENEMY_HIT_POINTS = 5;
    static final int MAX_ENEMY_HIT_POINTS = 20;
    static final int MIN_ENEMY_DAMAGE = 1;
    static final int MAX_ENEMY_DAMAGE = 5;

    //Entity ID: locationInWorld
    private final int damage;

    protected Enemy(Location locationInWorld, int hitPoints, int damage) {
        super(locationInWorld, hitPoints);
        checkArgument(damage >= 0, "Enemy damage amount must be positive");
        this.damage = damage;
    }

    @Override
    public int getAttackDamage() {
        return damage;
    }

    @Override
    protected void heal(int hitPointsBonus, int maxHitPoints) {
        throw new UnsupportedOperationException("Enemy cannot be healed");
    }
}
