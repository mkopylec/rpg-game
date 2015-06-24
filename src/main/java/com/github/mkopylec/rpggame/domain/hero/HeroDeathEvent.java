package com.github.mkopylec.rpggame.domain.hero;

import com.github.mkopylec.ddd.buildingblocks.DomainEvent;
import org.springframework.context.ApplicationEvent;

@DomainEvent
public class HeroDeathEvent extends ApplicationEvent {

    HeroDeathEvent(Hero source) {
        super(source);
    }

    public Hero getDeadHero() {
        return (Hero) getSource();
    }
}
