package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.rpggame.domain.world.Location;
import com.github.mkopylec.rpggame.domain.world.World;

import java.util.Set;
import java.util.UUID;

public class WorldInfo {

    private final UUID id;
    private final int width;
    private final int height;
    private final Set<Location> enemyLocations;

    private WorldInfo(UUID id, int width, int height, Set<Location> enemyLocations) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.enemyLocations = enemyLocations;
    }

    private void addEnemyLocation(Location location) {
        enemyLocations.add(location);
    }

    protected static WorldInfo fromWorld(World world) {
        return new WorldInfo(
                world.getId(),
                world.getWidth(),
                world.getHeight(),
                world.getSpawnedEnemiesLocations()
        );
    }
}
