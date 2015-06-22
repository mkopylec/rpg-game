package com.github.mkopylec.rpggame.domain.hero;

import com.github.mkopylec.ddd.buildingblocks.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface HeroRepository extends MongoRepository<Hero, String> {

}
