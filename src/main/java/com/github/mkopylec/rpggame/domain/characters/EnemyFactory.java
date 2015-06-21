package com.github.mkopylec.rpggame.domain.characters;

import com.github.mkopylec.ddd.buildingblocks.Factory;
import com.github.mkopylec.rpggame.domain.world.Dimension;
import com.github.mkopylec.rpggame.domain.world.Location;
import com.github.mkopylec.rpggame.infrastructure.random.RandomNumbersGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.mkopylec.rpggame.domain.characters.Enemy.MAX_ENEMY_DAMAGE;
import static com.github.mkopylec.rpggame.domain.characters.Enemy.MAX_ENEMY_HIT_POINTS;
import static com.github.mkopylec.rpggame.domain.characters.Enemy.MIN_ENEMY_DAMAGE;
import static com.github.mkopylec.rpggame.domain.characters.Enemy.MIN_ENEMY_HIT_POINTS;

@Factory
public class EnemyFactory {

    @Autowired
    private RandomNumbersGenerator numbersGenerator;

    public Enemy createEnemy(Dimension worldDimension) {
        return new Enemy(
                getRandomLocation(worldDimension),
                getRandomHitPoints(),
                getRandomDamage()
        );
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
