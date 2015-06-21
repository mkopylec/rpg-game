package com.github.mkopylec.rpggame.infrastructure.random;

import java.util.Random;

public final class ProbabilityResolver {

    public static boolean wasTrySuccessful(int successChanceInPercents) {
        Random random = new Random();
        int generatedPercentage = random.nextInt(99);
        return generatedPercentage < successChanceInPercents;
    }

    public static boolean wasTryUnsuccessful(int successChanceInPercents) {
        return !wasTrySuccessful(successChanceInPercents);
    }

    private ProbabilityResolver() {
    }
}
