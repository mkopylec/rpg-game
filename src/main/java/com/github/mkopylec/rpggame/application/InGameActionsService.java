package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.ddd.buildingblocks.ApplicationService;
import com.github.mkopylec.rpggame.domain.hero.Hero;
import com.github.mkopylec.rpggame.domain.hero.HeroRepository;
import com.github.mkopylec.rpggame.domain.services.BattleSimulator;
import com.github.mkopylec.rpggame.domain.services.HeroExplorationHandler;
import com.github.mkopylec.rpggame.domain.world.Enemy;
import com.github.mkopylec.rpggame.domain.world.Location;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@ApplicationService
public class InGameActionsService {

    private final WorldRepository worldRepository;
    private final HeroRepository heroRepository;
    private final HeroExplorationHandler explorationHandler;
    private final BattleSimulator battleSimulator;

    @Autowired
    public InGameActionsService(
            WorldRepository worldRepository,
            HeroRepository heroRepository,
            HeroExplorationHandler explorationHandler,
            BattleSimulator battleSimulator
    ) {
        this.worldRepository = worldRepository;
        this.heroRepository = heroRepository;
        this.explorationHandler = explorationHandler;
        this.battleSimulator = battleSimulator;
    }

    public void moveHero(UUID worldId, Location location) {
        World world = worldRepository.findOne(worldId);
        Hero hero = heroRepository.findOne(world.getSpawnedHeroName());

        explorationHandler.moveHeroToNewLocation(hero, world, location);

        if (explorationHandler.isHeroEngagingEnemy(hero, world)) {
            Enemy enemy = world.getEnemyAtLocation(location);
            battleSimulator.performBattle(hero, enemy);
        }
    }

    public List<DroppedItem> viewDroppedItems() {
        return null;
    }

    public List<DroppedItem> viewBackpackItems() {
        return null;
    }

    public void pickUpDroppedItem(UUID itemId) {

    }

    public void useItem(UUID itemId) {

    }
}
