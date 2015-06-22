package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.Repository;
import com.github.mkopylec.rpggame.infrastructure.persistance.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

@Repository
public class WorldRepository {

    private final InMemoryStorage storage;

    @Autowired
    public WorldRepository(InMemoryStorage storage) {
        this.storage = storage;
    }

    public void save(World world) {
        storage.put(world.getId(), world);
    }

    public World findOne(UUID id) {
        checkNotNull(id, "World id not provided");
        return (World) storage.get(id);
    }
}
