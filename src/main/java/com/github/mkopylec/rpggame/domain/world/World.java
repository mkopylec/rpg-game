package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;
import com.github.mkopylec.rpggame.domain.characters.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.unmodifiableList;
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
    private List<Enemy> enemies = new ArrayList<>();

    protected World(Dimension dimension) {
        this.dimension = checkNotNull(dimension, "Worlds dimension not provided");
    }

    public UUID getId() {
        return id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void spawnHero(String heroName) {
        checkArgument(isNotBlank(heroName), "Spawned hero name cannot be empty");
        this.heroName = heroName;
    }

    public List<Enemy> getSpawnedEnemies() {
        return unmodifiableList(enemies);
    }

    public void spawnEnemies(List<Enemy> enemies) {
        checkArgument(isNotEmpty(enemies), "No enemies provided to the world");
        this.enemies = enemies;
    }
}
