package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.Entity;
import com.github.mkopylec.rpggame.domain.services.BattleParticipant;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext;

@Entity
public class Enemy implements BattleParticipant {

    static final int MIN_ENEMY_HIT_POINTS = 5;
    static final int MAX_ENEMY_HIT_POINTS = 20;
    static final int MIN_ENEMY_DAMAGE = 1;
    static final int MAX_ENEMY_DAMAGE = 5;
    static final int MIN_ENEMIES_IN_WORLD = 10;
    static final int MAX_ENEMIES_IN_WORLD = 30;

    private final Location locationInWorld;//Entity ID
    private final int damage;
    private int hitPoints;

    Enemy(Location locationInWorld, int hitPoints, int damage) {
        checkArgument(hitPoints > 0, "Enemy hit points amount must be greater than 0");
        checkArgument(damage >= 0, "Enemy damage amount must be positive");
        this.locationInWorld = checkNotNull(locationInWorld, "No enemy location provided");
        this.hitPoints = hitPoints;
        this.damage = damage;
    }

    @Override
    public int getAttackDamage() {
        return damage;
    }

    @Override
    public void receiveAttack(int amountOfDamage) {
        checkArgument(amountOfDamage >= 0, "Cannot deal %s amount of damage", amountOfDamage);
        checkState(isAlive(), "Cannot deal damage to dead enemy");
        hitPoints -= amountOfDamage;
        if (isDead()) {
            notifyAboutDeath();
        }
    }

    @Override
    public boolean isAlive() {
        return hitPoints > 0;
    }

    public Location getLocationInWorld() {
        return locationInWorld;
    }

    public boolean isPlacedAtLocation(Location location) {
        return this.locationInWorld.equals(location);
    }

    public boolean isDead() {
        return !isAlive();
    }

    private void notifyAboutDeath() {
        EnemyDeathEvent deathEvent = new EnemyDeathEvent(this);
        getCurrentWebApplicationContext().publishEvent(deathEvent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Enemy)) return false;

        Enemy enemy = (Enemy) o;

        return new EqualsBuilder()
                .append(locationInWorld, enemy.locationInWorld)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(locationInWorld)
                .toHashCode();
    }
}
