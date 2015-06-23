package com.github.mkopylec.rpggame.domain.services;

public interface BattleParticipant {

    void receiveAttack(int amountOfDamage);

    int getAttackDamage();

    boolean isAlive();
}
