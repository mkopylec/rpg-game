package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.rpggame.domain.hero.HeroDeathEvent;
import com.github.mkopylec.rpggame.domain.hero.HeroRepository;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HeroDeathListener implements ApplicationListener<HeroDeathEvent> {

    private final HeroRepository heroRepository;
    private final WorldRepository worldRepository;

    @Autowired
    public HeroDeathListener(HeroRepository heroRepository, WorldRepository worldRepository) {
        this.heroRepository = heroRepository;
        this.worldRepository = worldRepository;
    }

    @Override
    public void onApplicationEvent(HeroDeathEvent event) {
        deleteHero(event);
        deleteWorld(event);
        throw new GameEndException("Hero " + event.getDeadHero().getName() + " died. Game over");
    }

    private void deleteHero(HeroDeathEvent event) {
        heroRepository.delete(event.getDeadHero());
    }

    private void deleteWorld(HeroDeathEvent event) {
        String heroName = event.getDeadHero().getName();
        World deadHeroWorld = worldRepository.findByHeroName(heroName);
        worldRepository.delete(deadHeroWorld);
    }
}
