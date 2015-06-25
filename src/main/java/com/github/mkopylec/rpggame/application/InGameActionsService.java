package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.ddd.buildingblocks.ApplicationService;
import com.github.mkopylec.rpggame.domain.hero.Hero;
import com.github.mkopylec.rpggame.domain.hero.HeroRepository;
import com.github.mkopylec.rpggame.domain.items.Item;
import com.github.mkopylec.rpggame.domain.services.BattleSimulator;
import com.github.mkopylec.rpggame.domain.world.Enemy;
import com.github.mkopylec.rpggame.domain.world.Location;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.github.mkopylec.rpggame.application.DroppedItem.fromItems;

@ApplicationService
public class InGameActionsService {

    private final WorldRepository worldRepository;
    private final HeroRepository heroRepository;
    private final BattleSimulator battleSimulator;

    @Autowired
    public InGameActionsService(
            WorldRepository worldRepository,
            HeroRepository heroRepository,
            BattleSimulator battleSimulator
    ) {
        this.worldRepository = worldRepository;
        this.heroRepository = heroRepository;
        this.battleSimulator = battleSimulator;
    }

    public void moveHero(UUID worldId, Location location) {
        World world = worldRepository.findOne(worldId);
        Hero hero = heroRepository.findOne(world.getSpawnedHeroName());

        world.moveHeroToNewLocation(location);

        if (world.isHeroEngagingEnemy()) {
            Enemy enemy = world.getEnemyAtLocation(location);
            battleSimulator.performBattle(hero, enemy);
        }
    }

    public List<DroppedItem> viewDroppedItems(UUID worldId, Location location) {
        World world = worldRepository.findOne(worldId);
        Set<Item> droppedItems = world.getDroppedItems(location);
        return fromItems(droppedItems);
    }

    public List<DroppedItem> viewBackpackItems(String heroName) {
        Hero hero = heroRepository.findOne(heroName);
        List<Item> backpackItems = hero.getBackpackItems();
        return fromItems(backpackItems);
    }

    public void pickUpDroppedItem(UUID itemId) {

    }

    public void useItem(UUID itemId) {

    }
}
