package com.github.mkopylec.rpggame.domain.world;

import org.springframework.context.ApplicationEvent;

public class EnemyDeathEvent extends ApplicationEvent {

    EnemyDeathEvent(Enemy source) {
        super(source);
    }

    public Enemy getDeadEnemy() {
        return (Enemy) getSource();
    }
}
