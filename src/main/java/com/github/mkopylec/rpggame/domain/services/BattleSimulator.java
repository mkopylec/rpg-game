package com.github.mkopylec.rpggame.domain.services;

import com.github.mkopylec.ddd.buildingblocks.DomainService;
import com.github.mkopylec.rpggame.domain.hero.Hero;
import com.github.mkopylec.rpggame.domain.world.Enemy;

@DomainService
public class BattleSimulator {

    public void performBattle(Hero hero, Enemy enemy) {
        BattleParticipant attacker = hero;
        BattleParticipant defender = enemy;

        while (areOpponentsAlive(hero, enemy)) {
            int attackDamage = attacker.getAttackDamage();
            defender.receiveAttack(attackDamage);

            attacker = changeBattleRole(attacker, hero, enemy);
            defender = changeBattleRole(defender, hero, enemy);
        }
    }

    private boolean areOpponentsAlive(Hero hero, Enemy enemy) {
        return hero.isAlive() && enemy.isAlive();
    }

    private BattleParticipant changeBattleRole(BattleParticipant participant, Hero hero, Enemy enemy) {
        return participant.equals(hero)
                ? enemy
                : hero;
    }
}
