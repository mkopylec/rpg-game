package com.github.mkopylec.rpggame.infrastructure.random;

import com.github.mkopylec.ddd.buildingblocks.InfrastructureService;
import com.github.mkopylec.rpggame.application.ProbabilityResolver;

import java.util.Random;

@InfrastructureService
public class ProbabilityResolverImpl implements ProbabilityResolver {

    @Override
    public boolean makeATry(int successChanceInPercents) {
        Random random = new Random();
        int generatedPercentage = random.nextInt(99);
        return generatedPercentage < successChanceInPercents;
    }
}
