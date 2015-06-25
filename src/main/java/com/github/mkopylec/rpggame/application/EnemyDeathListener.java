package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.rpggame.domain.items.Item;
import com.github.mkopylec.rpggame.domain.items.ItemFactory;
import com.github.mkopylec.rpggame.domain.world.Enemy;
import com.github.mkopylec.rpggame.domain.world.EnemyDeathEvent;
import com.github.mkopylec.rpggame.domain.world.Location;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class EnemyDeathListener implements ApplicationListener<EnemyDeathEvent> {

    private final ItemFactory itemFactory;
    private final WorldRepository worldRepository;

    @Autowired
    public EnemyDeathListener(ItemFactory itemFactory, WorldRepository worldRepository) {
        this.itemFactory = itemFactory;
        this.worldRepository = worldRepository;
    }

    @Override
    public void onApplicationEvent(EnemyDeathEvent event) {
        Enemy deadEnemy = event.getDeadEnemy();
        World deadEnemyWorld = worldRepository.findByEnemy(deadEnemy);

        if (event.shouldDropHealingPotion()) {
            dropHealingPotion(deadEnemyWorld, deadEnemy.getLocationInWorld());
        }
        if (event.shouldDropSword()) {
            dropSword(deadEnemyWorld, deadEnemy.getLocationInWorld());
        }
        deadEnemyWorld.removeDeadEnemy(deadEnemy);
    }

    private void dropHealingPotion(World world, Location potionLocation) {
        dropItem(world, potionLocation, itemFactory::createHealingPotion);
    }

    private void dropSword(World world, Location swordLocation) {
        dropItem(world, swordLocation, itemFactory::createSword);
    }

    private <I extends Item> void dropItem(World world, Location itemLocation, Supplier<I> itemCreator) {
        I item = itemCreator.get();
        world.placeDroppedItem(itemLocation, item);
    }
}
