package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.Entity;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
public class Enemy extends Character {

    static final int MIN_ENEMY_HIT_POINTS = 5;
    static final int MAX_ENEMY_HIT_POINTS = 20;
    static final int MIN_ENEMY_DAMAGE = 1;
    static final int MAX_ENEMY_DAMAGE = 5;
    static final int MIN_ENEMIES_IN_WORLD = 10;
    static final int MAX_ENEMIES_IN_WORLD = 30;

    //Entity ID: locationInWorld
    private final int damage;

    Enemy(Location locationInWorld, int hitPoints, int damage) {
        super(locationInWorld, hitPoints);
        checkArgument(damage >= 0, "Enemy damage amount must be positive");
        this.damage = damage;
    }

    @Override
    public int getAttackDamage() {
        return damage;
    }

    @Override
    public void move(Location location) {
        throw new UnsupportedOperationException("Enemy cannot be moved");
    }

    @Override
    protected void heal(int hitPointsBonus, int maxHitPoints) {
        throw new UnsupportedOperationException("Enemy cannot be healed");
    }
}
