package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;
import com.github.mkopylec.rpggame.domain.characters.Enemy;

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

    protected World(Dimension dimension) {
        this.dimension = checkNotNull(dimension, "Worlds dimension not provided");
    }

    public UUID getId() {
        return id;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

    public String getHeroName() {
        return heroName;
    }

    public void spawnHero(String heroName) {
        checkArgument(isNotBlank(heroName), "Spawned hero name cannot be empty");
        this.heroName = heroName;
    }

    public Set<Enemy> getSpawnedEnemies() {
        return unmodifiableSet(enemies);
    }

    public void spawnEnemies(Set<Enemy> enemies) {
        checkArgument(isNotEmpty(enemies), "No enemies provided to the world");
        this.enemies = enemies;
    }
}
