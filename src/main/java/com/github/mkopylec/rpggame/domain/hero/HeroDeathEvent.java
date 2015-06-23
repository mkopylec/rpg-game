package com.github.mkopylec.rpggame.domain.hero;

import org.springframework.context.ApplicationEvent;

public class HeroDeathEvent extends ApplicationEvent {

    HeroDeathEvent(Hero source) {
        super(source);
    }

    public Hero getDeadHero() {
        return (Hero) getSource();
    }
}
