package com.github.mkopylec.rpggame.domain.characters;

import com.github.mkopylec.ddd.buildingblocks.Factory;
import com.github.mkopylec.rpggame.domain.world.Dimension;
import com.github.mkopylec.rpggame.domain.world.Location;
import com.github.mkopylec.rpggame.infrastructure.random.RandomNumbersGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static com.github.mkopylec.rpggame.domain.characters.Enemy.MAX_ENEMIES_IN_WORLD;
import static com.github.mkopylec.rpggame.domain.characters.Enemy.MAX_ENEMY_DAMAGE;
import static com.github.mkopylec.rpggame.domain.characters.Enemy.MAX_ENEMY_HIT_POINTS;
import static com.github.mkopylec.rpggame.domain.characters.Enemy.MIN_ENEMIES_IN_WORLD;
import static com.github.mkopylec.rpggame.domain.characters.Enemy.MIN_ENEMY_DAMAGE;
import static com.github.mkopylec.rpggame.domain.characters.Enemy.MIN_ENEMY_HIT_POINTS;

@Factory
public class EnemyFactory {

    private final RandomNumbersGenerator numbersGenerator;

    @Autowired
    public EnemyFactory(RandomNumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public Set<Enemy> createRandomNumberOfEnemies(Dimension worldDimension) {
        int numberOfEnemies = numbersGenerator.getRandomInt(MIN_ENEMIES_IN_WORLD, MAX_ENEMIES_IN_WORLD);
        Set<Enemy> enemies = new HashSet<>(numberOfEnemies);

        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy enemy = new Enemy(
                    getRandomLocation(worldDimension),
                    getRandomHitPoints(),
                    getRandomDamage()
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
}
