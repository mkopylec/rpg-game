package com.github.mkopylec.rpggame.domain.services;

import com.github.mkopylec.ddd.buildingblocks.DomainService;
import com.github.mkopylec.rpggame.domain.hero.Hero;
import com.github.mkopylec.rpggame.domain.world.Location;
import com.github.mkopylec.rpggame.domain.world.World;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@DomainService
public class HeroExplorationHandler {

    public void moveHeroToNewLocation(Hero hero, World world, Location location) {
        checkNotNull(hero, "Hero not provided");
        checkNotNull(world, "World not provided");
        checkNotNull(location, "Location not provided");
        checkArgument(world.containsLocation(location), "Location is out of the world");
        hero.move(location);
    }

    public boolean isHeroEngagingEnemy(Hero hero, World world) {
        checkNotNull(hero, "Hero not provided");
        checkNotNull(world, "World not provided");
        return world.hasEnemyAtLocation(hero.getLocationInWorld());
    }
}
