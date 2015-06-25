package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.DomainEvent;
import org.springframework.context.ApplicationEvent;

@DomainEvent
public class EnemyDeathEvent extends ApplicationEvent {

    EnemyDeathEvent(Enemy source) {
        super(source);
    }

    public boolean shouldDropHealingPotion() {
        return ((Enemy) getSource()).shouldDropHealingPotion();
    }

    public boolean shouldDropSword() {
        return ((Enemy) getSource()).shouldDropSword();
    }

    public Enemy getDeadEnemy() {
        return (Enemy) getSource();
    }
}
