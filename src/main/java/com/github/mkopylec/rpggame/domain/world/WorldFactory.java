package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.Factory;
import com.github.mkopylec.rpggame.infrastructure.random.RandomNumbersGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.mkopylec.rpggame.domain.world.World.MAX_WORLD_HEIGHT;
import static com.github.mkopylec.rpggame.domain.world.World.MAX_WORLD_WIDTH;
import static com.github.mkopylec.rpggame.domain.world.World.MIN_WORLD_HEIGHT;
import static com.github.mkopylec.rpggame.domain.world.World.MIN_WORLD_WIDTH;

@Factory
public class WorldFactory {

    private final RandomNumbersGenerator numbersGenerator;

    @Autowired
    public WorldFactory(RandomNumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public World createWorld() {
        return new World(getRandomDimension());
    }

    private Dimension getRandomDimension() {
        int height = numbersGenerator.getRandomInt(MIN_WORLD_HEIGHT, MAX_WORLD_HEIGHT);
        int width = numbersGenerator.getRandomInt(MIN_WORLD_WIDTH, MAX_WORLD_WIDTH);
        return new Dimension(width, height);
    }
}
