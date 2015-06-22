package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.rpggame.domain.characters.Enemy;
import com.github.mkopylec.rpggame.domain.world.Location;
import com.github.mkopylec.rpggame.domain.world.World;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WorldInfo {

    private final UUID id;
    private final int width;
    private final int height;
    private final Set<Location> enemyLocations = new HashSet<>();

    private WorldInfo(UUID id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    private void addEnemyLocation(Location location) {
        enemyLocations.add(location);
    }

    protected static WorldInfo fromWorld(World world) {
        WorldInfo worldInfo = new WorldInfo(
                world.getId(),
                world.getWidth(),
                world.getHeight()
        );
        for (Enemy enemy : world.getSpawnedEnemies()) {
            worldInfo.addEnemyLocation(enemy.getLocationInWorld());
        }
        return worldInfo;
    }
}
