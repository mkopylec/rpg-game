package com.github.mkopylec.rpggame.domain.characters;

import com.github.mkopylec.ddd.buildingblocks.DomainService;

import static com.github.mkopylec.rpggame.domain.characters.BattleResult.fromCharacter;

@DomainService
public class BattleSimulator {

    public BattleResult pursueBattle(Hero hero, Enemy enemy) {
        Character attacker = hero;
        Character defender = enemy;

        while (areOpponentsAlive(hero, enemy)) {
            int attackDamage = attacker.getAttackDamage();
            defender.receiveAttack(attackDamage);

            attacker = changeCharacter(attacker, hero, enemy);
            defender = changeCharacter(defender, hero, enemy);
        }

        return attacker.isAlive()
                ? fromCharacter(attacker)
                : fromCharacter(enemy);
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
