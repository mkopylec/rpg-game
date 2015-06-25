package com.github.mkopylec.rpggame.domain.hero;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;
import com.github.mkopylec.rpggame.domain.items.HealingPotion;
import com.github.mkopylec.rpggame.domain.items.Item;
import com.github.mkopylec.rpggame.domain.items.Sword;
import com.github.mkopylec.rpggame.domain.services.BattleParticipant;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext;

@Entity
@AggregateRoot
public class Hero implements BattleParticipant {

    private static final int MAX_HIT_POINTS = 100;

    @Id
    private final String name;//Entity ID
    private Sword equippedSword;
    private final Backpack backpack = new Backpack();
    private int hitPoints = MAX_HIT_POINTS;

    public Hero(String name, Sword equippedSword) {
        checkArgument(isNotBlank(name), "Empty hero name");
        this.name = name;
        this.equippedSword = checkNotNull(equippedSword, "Sword to equip by hero not provided");
    }

    @Override
    public int getAttackDamage() {
        return equippedSword == null ? 0 : equippedSword.getDamage();
    }

    @Override
    public void receiveAttack(int amountOfDamage) {
        checkArgument(amountOfDamage >= 0, "Cannot deal %s amount of damage", amountOfDamage);
        checkState(isAlive(), "Cannot deal damage to dead hero");
        hitPoints -= amountOfDamage;
        if (isDead()) {
            notifyAboutDeath();
        }
    }

    @Override
    public boolean isAlive() {
        return hitPoints > 0;
    }

    public String getName() {
        return name;
    }

    public void equipSword(UUID swordId) {
        Item item = backpack.getItem(swordId);
        checkArgument(item instanceof Sword, "Item with id: %s is not a sword", swordId);
        takeOfEquippedSword();
        this.equippedSword = (Sword) item;
    }

    public void drinkHealingPotion(UUID potionId) {
        Item item = backpack.getItem(potionId);
        checkArgument(item instanceof HealingPotion, "Item with id: %s is not a healing potion", potionId);
        HealingPotion potion = (HealingPotion) item;
        heal(potion.getHitPointsBonus());
    }

    public List<Item> getBackpackItems() {
        return backpack.getItems();
    }

    private void takeOfEquippedSword() {
        backpack.putItem(equippedSword);
        equippedSword = null;
    }

    private boolean isDead() {
        return !isAlive();
    }

    private void heal(int hitPointsBonus) {
        int totalHp = hitPoints + hitPointsBonus;
        hitPoints = totalHp > MAX_HIT_POINTS
                ? MAX_HIT_POINTS
                : totalHp;
    }

    private void notifyAboutDeath() {
        HeroDeathEvent deathEvent = new HeroDeathEvent(this);
        getCurrentWebApplicationContext().publishEvent(deathEvent);
    }
}
