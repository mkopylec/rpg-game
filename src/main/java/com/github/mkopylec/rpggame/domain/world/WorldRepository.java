package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.Repository;
import com.github.mkopylec.rpggame.infrastructure.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;

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
}
