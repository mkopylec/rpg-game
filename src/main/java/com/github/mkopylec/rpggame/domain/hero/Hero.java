package com.github.mkopylec.rpggame.domain.hero;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;
import com.github.mkopylec.rpggame.domain.items.HealingPotion;
import com.github.mkopylec.rpggame.domain.items.Item;
import com.github.mkopylec.rpggame.domain.items.Sword;
import com.github.mkopylec.rpggame.domain.world.Character;
import com.github.mkopylec.rpggame.domain.world.Location;
import org.springframework.data.annotation.Id;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@AggregateRoot
public class Hero extends Character {

    private static final int STARTING_SWORD_DAMAGE = 1;
    private static final int MAX_HIT_POINTS = 100;

    @Id
    private final String name;//Entity ID
    private Sword equippedSword = new Sword(STARTING_SWORD_DAMAGE);
    private final Backpack backpack = new Backpack();

    public Hero(String name) {
        super(new Location(), MAX_HIT_POINTS);
        checkArgument(isNotBlank(name), "Empty hero name");
        this.name = name;
    }

    @Override
    public int getAttackDamage() {
        return equippedSword == null
                ? 0
                : equippedSword.getDamage();
    }

    public void equipSword(UUID swordId) {
        Item item = backpack.getItem(swordId);
        checkArgument(item instanceof Sword, "Item with id: %s is not a sword", swordId);
        this.equippedSword = (Sword) item;
    }

    public void takeOfEquippedSword() {
        backpack.putItem(equippedSword);
        equippedSword = null;
    }

    public void drinkHealingPotion(UUID potionId) {
        Item item = backpack.getItem(potionId);
        checkArgument(item instanceof HealingPotion, "Item with id: %s is not a healing potion", potionId);
        HealingPotion potion = (HealingPotion) item;
        heal(potion.getHitPointsBonus(), MAX_HIT_POINTS);
    }
}
