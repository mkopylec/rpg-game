package com.github.mkopylec.rpggame.domain.services;

import com.github.mkopylec.ddd.buildingblocks.DomainService;
import com.github.mkopylec.rpggame.domain.hero.Hero;
import com.github.mkopylec.rpggame.domain.world.Character;
import com.github.mkopylec.rpggame.domain.world.Enemy;

@DomainService
public class BattleSimulator {

    public BattleResult performBattle(Hero hero, Enemy enemy) {
        Character attacker = hero;
        Character defender = enemy;

        while (areOpponentsAlive(hero, enemy)) {
            int attackDamage = attacker.getAttackDamage();
            defender.receiveAttack(attackDamage);

            attacker = changeCharacter(attacker, hero, enemy);
            defender = changeCharacter(defender, hero, enemy);
        }

        return attacker.isAlive()
                ? BattleResult.fromCharacter(attacker)
                : BattleResult.fromCharacter(enemy);
    }

    private boolean areOpponentsAlive(Hero hero, Enemy enemy) {
        return hero.isAlive() && enemy.isAlive();
    }

    private Character changeCharacter(Character character, Hero hero, Enemy enemy) {
        return character.equals(hero)
                ? enemy
                : hero;
    }
}
