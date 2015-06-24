package com.github.mkopylec.rpggame.infrastructure.persistance;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
class InMemoryStorage {

    private final ConcurrentMap<Object, Object> storage = new ConcurrentHashMap<>();

    void put(Object id, Object object) {
        storage.put(id, object);
    }

    @SuppressWarnings("unchecked")
    Object get(Object id) {
        return storage.get(id);
    }

    Collection<Object> getAll() {
        return storage.values();
    }

    void remove(Object id) {
        storage.remove(id);
    }
}
