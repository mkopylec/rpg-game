package com.github.mkopylec.rpggame.domain.services;

import com.github.mkopylec.rpggame.domain.hero.Hero;
import com.github.mkopylec.rpggame.domain.world.Enemy;

public enum BattleResult {

    HERO_WIN,
    ENEMY_WIN;

    static BattleResult fromCharacter(com.github.mkopylec.rpggame.domain.world.Character character) {
        if (character instanceof Hero) {
            return HERO_WIN;
        } else if (character instanceof Enemy) {
            return ENEMY_WIN;
        }
        throw new IllegalArgumentException("Unknown character type " + character.getClass().getSimpleName());
    }
}
