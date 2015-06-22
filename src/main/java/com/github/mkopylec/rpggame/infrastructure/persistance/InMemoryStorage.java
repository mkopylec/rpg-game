package com.github.mkopylec.rpggame.infrastructure.persistance;

import com.github.mkopylec.ddd.buildingblocks.InfrastructureService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@InfrastructureService
public class InMemoryStorage {

    private final ConcurrentMap<Object, Object> storage = new ConcurrentHashMap<>();

    public void put(Object id, Object object) {
        storage.put(id, object);
    }

    @SuppressWarnings("unchecked")
    public Object get(Object id) {
        return storage.get(id);
    }
}
