package com.github.mkopylec.rpggame.infrastructure.persistance;

import com.github.mkopylec.rpggame.domain.world.Enemy;
import com.github.mkopylec.rpggame.domain.world.World;
import com.github.mkopylec.rpggame.domain.world.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.collections4.CollectionUtils.find;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class WorldRepositoryImpl implements WorldRepository {

    private final InMemoryStorage storage;

    @Autowired
    public WorldRepositoryImpl(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void save(World world) {
        checkNotNull(world, "World not provided");
        storage.put(world.getId(), world);
    }

    @Override
    public World findOne(UUID id) {
        checkNotNull(id, "World id not provided");
        return (World) storage.get(id);
    }

    @Override
    public World findByHeroName(String heroName) {
        checkArgument(isNotBlank(heroName), "Empty hero name");
        return getWorld(world -> world.hasSpawnedHero(heroName));
    }

    @Override
    public World findByEnemy(Enemy enemy) {
        checkNotNull(enemy, "Enemy not provided");
        return getWorld(world -> world.hasSpawnedEnemy(enemy));
    }

    @Override
    public void delete(World world) {
        checkNotNull(world, "World not provided");
        storage.remove(world.getId());
    }

    private World getWorld(Predicate<World> queryCondition) {
        Collection<Object> allObjects = storage.getAll();
        return (World) find(allObjects, object -> {
            if (object instanceof World) {
                return queryCondition.test((World) object);
            }
            return false;
        });
    }
}
