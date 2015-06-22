package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.ddd.buildingblocks.ApplicationService;
import com.github.mkopylec.rpggame.domain.characters.Enemy;
import com.github.mkopylec.rpggame.domain.characters.EnemyFactory;
import com.github.mkopylec.rpggame.domain.characters.Hero;
import com.github.mkopylec.rpggame.domain.characters.HeroRepository;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldFactory;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static com.github.mkopylec.rpggame.application.WorldInfo.fromWorld;

@ApplicationService
public class GameService {

    private final HeroRepository heroRepository;
    private final WorldFactory worldFactory;
    private final WorldRepository worldRepository;
    private final EnemyFactory enemyFactory;

    @Autowired
    public GameService(HeroRepository heroRepository, WorldFactory worldFactory, WorldRepository worldRepository, EnemyFactory enemyFactory) {
        this.heroRepository = heroRepository;
        this.worldFactory = worldFactory;
        this.worldRepository = worldRepository;
        this.enemyFactory = enemyFactory;
    }

    public WorldInfo createNewGame(String heroName) {
        World world = worldFactory.createWorld();
        world.spawnHero(heroName);
        Set<Enemy> enemies = enemyFactory.createRandomNumberOfEnemies(world.getDimension());
        world.spawnEnemies(enemies);

        worldRepository.save(world);

        return fromWorld(world);
    }

    public void createHero(String heroName) {
        Hero hero = new Hero(heroName);
        heroRepository.save(hero);
    }
}
