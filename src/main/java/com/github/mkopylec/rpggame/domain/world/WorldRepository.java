package com.github.mkopylec.rpggame.domain.world;

import com.github.mkopylec.ddd.buildingblocks.Repository;

import java.util.UUID;

@Repository
public interface WorldRepository {

    void save(World world);

    World findOne(UUID id);

    World findByHeroName(String heroName);

    World findByEnemy(Enemy enemy);

    void delete(World world);
}
