package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.Factory;
import com.github.mkopylec.rpggame.domain.services.ProbabilityResolver;
import com.github.mkopylec.rpggame.domain.services.RandomNumbersGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static com.github.mkopylec.rpggame.domain.world.Enemy.MAX_ENEMIES_IN_WORLD;
import static com.github.mkopylec.rpggame.domain.world.Enemy.MAX_ENEMY_DAMAGE;
import static com.github.mkopylec.rpggame.domain.world.Enemy.MAX_ENEMY_HIT_POINTS;
import static com.github.mkopylec.rpggame.domain.world.Enemy.MIN_ENEMIES_IN_WORLD;
import static com.github.mkopylec.rpggame.domain.world.Enemy.MIN_ENEMY_DAMAGE;
import static com.github.mkopylec.rpggame.domain.world.Enemy.MIN_ENEMY_HIT_POINTS;
import static com.github.mkopylec.rpggame.domain.world.World.MAX_WORLD_HEIGHT;
import static com.github.mkopylec.rpggame.domain.world.World.MAX_WORLD_WIDTH;
import static com.github.mkopylec.rpggame.domain.world.World.MIN_WORLD_HEIGHT;
import static com.github.mkopylec.rpggame.domain.world.World.MIN_WORLD_WIDTH;

@Factory
public class WorldFactory {

    private static final int CHANCE_TO_DROP_HEALING_POTION = 15;
    private static final int CHANCE_TO_DROP_SWORD = 10;

    private final RandomNumbersGenerator numbersGenerator;
    private final ProbabilityResolver probabilityResolver;

    @Autowired
    public WorldFactory(RandomNumbersGenerator numbersGenerator, ProbabilityResolver probabilityResolver) {
        this.numbersGenerator = numbersGenerator;
        this.probabilityResolver = probabilityResolver;
    }

    public World createWorld(String heroName) {
        World world = new World(getRandomDimension());
        world.spawnHero(heroName);
        Set<Enemy> enemies = createRandomNumberOfEnemies(world.getDimension());
        world.spawnEnemies(enemies);

        return world;
    }

    private Dimension getRandomDimension() {
        int height = numbersGenerator.getRandomInt(MIN_WORLD_HEIGHT, MAX_WORLD_HEIGHT);
        int width = numbersGenerator.getRandomInt(MIN_WORLD_WIDTH, MAX_WORLD_WIDTH);
        return new Dimension(width, height);
    }

    private Set<Enemy> createRandomNumberOfEnemies(Dimension worldDimension) {
        int numberOfEnemies = numbersGenerator.getRandomInt(MIN_ENEMIES_IN_WORLD, MAX_ENEMIES_IN_WORLD);
        Set<Enemy> enemies = new HashSet<>(numberOfEnemies);

        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy enemy = new Enemy(
                    getRandomLocation(worldDimension),
                    getRandomHitPoints(),
                    getRandomDamage(),
                    shouldDropHealingPotion(),
                    shouldDropSword()
            );
            enemies.add(enemy);
        }

        return enemies;
    }

    private Location getRandomLocation(Dimension worldDimension) {
        int y = numbersGenerator.getRandomInt(0, worldDimension.getHeight());
        int x = numbersGenerator.getRandomInt(0, worldDimension.getWidth());
        return new Location(x, y);
    }

    private int getRandomHitPoints() {
        return numbersGenerator.getRandomInt(MIN_ENEMY_HIT_POINTS, MAX_ENEMY_HIT_POINTS);
    }

    private int getRandomDamage() {
        return numbersGenerator.getRandomInt(MIN_ENEMY_DAMAGE, MAX_ENEMY_DAMAGE);
    }

    private boolean shouldDropHealingPotion() {
        return probabilityResolver.makeATry(CHANCE_TO_DROP_HEALING_POTION);
    }

    private boolean shouldDropSword() {
        return probabilityResolver.makeATry(CHANCE_TO_DROP_SWORD);
    }
}
