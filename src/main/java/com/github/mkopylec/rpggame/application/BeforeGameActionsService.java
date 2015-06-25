package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.ddd.buildingblocks.ApplicationService;
import com.github.mkopylec.rpggame.domain.hero.Hero;
import com.github.mkopylec.rpggame.domain.hero.HeroRepository;
import com.github.mkopylec.rpggame.domain.items.ItemFactory;
import com.github.mkopylec.rpggame.domain.items.Sword;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldFactory;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.mkopylec.rpggame.application.WorldInfo.fromWorld;

@ApplicationService
public class BeforeGameActionsService {

    private final HeroRepository heroRepository;
    private final WorldFactory worldFactory;
    private final WorldRepository worldRepository;
    private final ItemFactory itemFactory;

    @Autowired
    public BeforeGameActionsService(
            HeroRepository heroRepository,
            WorldFactory worldFactory,
            WorldRepository worldRepository,
            ItemFactory itemFactory
    ) {
        this.heroRepository = heroRepository;
        this.worldFactory = worldFactory;
        this.worldRepository = worldRepository;
        this.itemFactory = itemFactory;
    }

    public WorldInfo createNewGame(String heroName) {
        World world = worldFactory.createWorld(heroName);
        worldRepository.save(world);
        return fromWorld(world);
    }

    public void createHero(String heroName) {
        Sword sword = itemFactory.createStartingSword();
        Hero hero = new Hero(heroName, sword);
        heroRepository.save(hero);
    }
}
