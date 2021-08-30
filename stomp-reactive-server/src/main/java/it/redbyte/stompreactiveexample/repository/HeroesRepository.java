package it.redbyte.stompreactiveexample.repository;

import it.redbyte.stompreactiveexample.model.entities.Hero;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroesRepository extends ReactiveMongoRepository<Hero, String> {
}
