package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.AggregateRoot;
import com.github.mkopylec.ddd.buildingblocks.Entity;
import com.github.mkopylec.rpggame.domain.items.Item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    private Location heroLocation;
    private Set<Enemy> enemies = new HashSet<>();
    private final Map<Location, Set<Item>> droppedItems = new HashMap<>();

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

    public void placeDroppedItem(Location location, Item item) {
        checkNotNull(location, "Item location not provided");
        checkArgument(containsLocation(location), "Item location is out of the world");
        if (!droppedItems.containsKey(location)) {
            droppedItems.put(location, new HashSet<>());
        }
        droppedItems.get(location).add(checkNotNull(item, "Dropped item not provided"));
    }

    public Set<Item> getDroppedItems(Location itemsLocation) {
        checkNotNull(itemsLocation, "Dropped items location not provided");
        Set<Item> items = droppedItems.getOrDefault(itemsLocation, new HashSet<>());
        return unmodifiableSet(items);
    }

    public boolean isHeroEngagingEnemy() {
        return hasEnemyAtLocation(heroLocation);
    }

    public void moveHeroToNewLocation(Location newLocation) {
        checkNotNull(newLocation, "New location not provided");
        checkArgument(containsLocation(newLocation), "New location is out of the world");
        heroLocation = newLocation;
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

    public boolean hasSpawnedHero(String heroName) {
        checkArgument(isNotBlank(heroName), "Spawned hero name cannot be empty");
        return this.heroName.equals(heroName);
    }

    public boolean hasSpawnedEnemy(Enemy enemy) {
        checkNotNull(enemy, "Spawned enemy not provided");
        return enemies.contains(enemy);
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
            if (enemy.isPlacedAtLocation(location)) {
                return enemy;
            }
        }
        throw new IllegalArgumentException("No enemy at provided world location");
    }

    public void removeDeadEnemy(Enemy enemy) {
        checkNotNull(enemy, "Dead enemy not provided");
        checkArgument(enemy.isDead(), "Cannot remove living enemy from the world");
        enemies.remove(enemy);
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

    private boolean containsLocation(Location location) {
        checkNotNull(location, "No world location provided");
        return location.getX() < dimension.getWidth() &&
                location.getY() < dimension.getHeight();
    }
}
