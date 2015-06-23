package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.rpggame.domain.hero.HeroDeathEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GameEndListener implements ApplicationListener<HeroDeathEvent> {

    @Override
    public void onApplicationEvent(HeroDeathEvent event) {

    }
}
