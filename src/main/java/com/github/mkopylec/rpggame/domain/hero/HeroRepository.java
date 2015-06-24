package com.github.mkopylec.rpggame.domain.hero;

import com.github.mkopylec.ddd.buildingblocks.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface HeroRepository extends CrudRepository<Hero, String> {

}
