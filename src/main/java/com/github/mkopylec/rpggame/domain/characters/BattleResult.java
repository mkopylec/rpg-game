package com.github.mkopylec.rpggame.domain.characters;

public enum BattleResult {

    HERO_WIN,
    ENEMY_WIN;

    protected static BattleResult fromCharacter(Character character) {
        if (character instanceof Hero) {
            return HERO_WIN;
        } else if (character instanceof Enemy) {
            return ENEMY_WIN;
        }
        throw new IllegalArgumentException("Unknown character type " + character.getClass().getSimpleName());
    }
}
