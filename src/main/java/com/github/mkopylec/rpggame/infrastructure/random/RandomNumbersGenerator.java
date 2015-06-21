package com.github.mkopylec.rpggame.infrastructure.random;

import com.github.mkopylec.ddd.buildingblocks.InfrastructureService;

import java.util.Random;

@InfrastructureService
public class RandomNumbersGenerator {

    public int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
