package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.rpggame.domain.items.Item;
import com.github.mkopylec.rpggame.domain.items.ItemFactory;
import com.github.mkopylec.rpggame.domain.items.ItemRepository;
import com.github.mkopylec.rpggame.domain.world.Enemy;
import com.github.mkopylec.rpggame.domain.world.EnemyDeathEvent;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class EnemyDeathListener implements ApplicationListener<EnemyDeathEvent> {

    private static final int CHANCE_TO_DROP_HEALING_POTION = 15;
    private static final int CHANCE_TO_DROP_SWORD = 10;

    private final ItemFactory itemFactory;
    private final ItemRepository itemRepository;
    private final ProbabilityResolver probabilityResolver;
    private final WorldRepository worldRepository;

    @Autowired
    public EnemyDeathListener(
            ItemFactory itemFactory,
            ItemRepository itemRepository,
            ProbabilityResolver probabilityResolver,
            WorldRepository worldRepository
    ) {
        this.itemFactory = itemFactory;
        this.itemRepository = itemRepository;
        this.probabilityResolver = probabilityResolver;
        this.worldRepository = worldRepository;
    }

    @Override
    public void onApplicationEvent(EnemyDeathEvent event) {
        tryToDropHealingPotion();
        tryToDropSword();
        removeDeadEnemyFromWorld(event.getDeadEnemy());
    }

    private void tryToDropHealingPotion() {
        tryToDropItem(CHANCE_TO_DROP_HEALING_POTION, itemFactory::createHealingPotion);
    }

    private void tryToDropSword() {
        tryToDropItem(CHANCE_TO_DROP_SWORD, itemFactory::createSword);
    }

    private void removeDeadEnemyFromWorld(Enemy enemy) {
        World deadEnemyWorld = worldRepository.findByEnemy(enemy);
        deadEnemyWorld.removeDeadEnemy(enemy);
    }

    private <I extends Item> void tryToDropItem(int chanceToDrop, Supplier<I> itemCreator) {
        boolean shouldDropItem = probabilityResolver.makeATry(chanceToDrop);
        if (shouldDropItem) {
            I item = itemCreator.get();
            itemRepository.save(item);
        }
    }
}
