package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.unmodifiableSet;
import static java.util.UUID.randomUUID;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@AggregateRoot
public class World {

    static final int MIN_WORLD_WIDTH = 30;
    static final int MAX_WORLD_WIDTH = 300;
    static final int MIN_WORLD_HEIGHT = 20;
    static final int MAX_WORLD_HEIGHT = 200;

    private final UUID id = randomUUID();//Entity ID
    private final Dimension dimension;
    private String heroName;
    private Set<Enemy> enemies = new HashSet<>();

    World(Dimension dimension) {
        this.dimension = checkNotNull(dimension, "Worlds dimension not provided");
    }

    public UUID getId() {
        return id;
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

    public Set<Location> getSpawnedEnemiesLocations() {
        Set<Location> locations = new HashSet<>(enemies.size());
        for (Enemy enemy : enemies) {
            locations.add(enemy.getLocationInWorld());
        }
        return unmodifiableSet(locations);
    }

    public String getSpawnedHeroName() {
        return heroName;
    }

    public boolean containsLocation(Location location) {
        checkNotNull(location, "No world location provided");
        return location.getX() < dimension.getWidth() &&
                location.getY() < dimension.getHeight();
    }

    public boolean hasEnemyAtLocation(Location location) {
        try {
            return getEnemyAtLocation(location) != null;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public Enemy getEnemyAtLocation(Location location) {
        checkNotNull(location, "World location not provided");
        for (Enemy enemy : enemies) {
            if (enemy.getLocationInWorld().equals(location)) {
                return enemy;
            }
        }
        throw new IllegalArgumentException("No enemy at provided world location");
    }

    Dimension getDimension() {
        return dimension;
    }

    void spawnHero(String heroName) {
        checkArgument(isNotBlank(heroName), "Spawned hero name cannot be empty");
        this.heroName = heroName;
    }

    void spawnEnemies(Set<Enemy> enemies) {
        checkArgument(isNotEmpty(enemies), "No enemies provided to the world");
        this.enemies = enemies;
    }
}
