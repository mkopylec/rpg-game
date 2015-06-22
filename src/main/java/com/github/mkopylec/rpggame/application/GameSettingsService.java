package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.ddd.buildingblocks.ApplicationService;
import com.github.mkopylec.rpggame.domain.hero.Hero;
import com.github.mkopylec.rpggame.domain.hero.HeroRepository;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldFactory;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.mkopylec.rpggame.application.WorldInfo.fromWorld;

@ApplicationService
public class GameSettingsService {

    private final HeroRepository heroRepository;
    private final WorldFactory worldFactory;
    private final WorldRepository worldRepository;

    @Autowired
    public GameSettingsService(HeroRepository heroRepository, WorldFactory worldFactory, WorldRepository worldRepository) {
        this.heroRepository = heroRepository;
        this.worldFactory = worldFactory;
        this.worldRepository = worldRepository;
    }

    public WorldInfo createNewGame(String heroName) {
        World world = worldFactory.createWorld(heroName);
        worldRepository.save(world);
        return fromWorld(world);
    }

    public void createHero(String heroName) {
        Hero hero = new Hero(heroName);
        heroRepository.save(hero);
    }
}
