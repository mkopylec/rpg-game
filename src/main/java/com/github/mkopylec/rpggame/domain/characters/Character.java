package com.github.mkopylec.rpggame.domain.characters;

import com.github.mkopylec.rpggame.domain.world.Location;
import org.springframework.data.annotation.Transient;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

abstract class Character {

    @Transient
    private final Location locationInWorld;
    private int hitPoints;

    protected Character(Location locationInWorld, int hitPoints) {
        checkArgument(hitPoints > 0, "Enemy hit points amount must be greater than 0");
        this.locationInWorld = checkNotNull(locationInWorld, "No character location provided");
        this.hitPoints = hitPoints;
    }

    public void receiveAttack(int amountOfDamage) {
        checkArgument(amountOfDamage >= 0, "Cannot deal %s amount of damage", amountOfDamage);
        checkState(isAlive(), "Cannot deal damage to dead character");
        hitPoints -= amountOfDamage;
    }

    public abstract int getAttackDamage();

    public Location getLocationInWorld() {
        return locationInWorld;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    protected void heal(int hitPointsBonus, int maxHitPoints) {
        int totalHp = hitPoints + hitPointsBonus;
        hitPoints = totalHp > maxHitPoints
                ? maxHitPoints
                : totalHp;
    }
}
