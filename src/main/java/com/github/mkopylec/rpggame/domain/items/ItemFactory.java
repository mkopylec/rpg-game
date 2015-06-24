package com.github.mkopylec.rpggame.domain.items;

import com.github.mkopylec.ddd.buildingblocks.Factory;
import com.github.mkopylec.rpggame.domain.services.RandomNumbersGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.mkopylec.rpggame.domain.items.HealingPotion.MAX_POTION_HP_BONUS;
import static com.github.mkopylec.rpggame.domain.items.HealingPotion.MIN_POTION_HP_BONUS;
import static com.github.mkopylec.rpggame.domain.items.Sword.MAX_SWORD_DAMAGE;
import static com.github.mkopylec.rpggame.domain.items.Sword.MIN_SWORD_DAMAGE;

@Factory
public class ItemFactory {

    private final RandomNumbersGenerator numbersGenerator;

    @Autowired
    public ItemFactory(RandomNumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public HealingPotion createHealingPotion() {
        int hitPointsBonus = numbersGenerator.getRandomInt(MIN_POTION_HP_BONUS, MAX_POTION_HP_BONUS);
        return new HealingPotion(hitPointsBonus);
    }

    public Sword createSword() {
        int damage = numbersGenerator.getRandomInt(MIN_SWORD_DAMAGE, MAX_SWORD_DAMAGE);
        return new Sword(damage);
    }
}
