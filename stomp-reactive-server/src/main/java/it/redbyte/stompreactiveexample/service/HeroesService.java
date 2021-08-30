package it.redbyte.stompreactiveexample.service;

import it.redbyte.stompreactiveexample.event.HeroCreatedEvent;
import it.redbyte.stompreactiveexample.event.HeroDeletedEvent;
import it.redbyte.stompreactiveexample.event.HeroUpdatedEvent;
import it.redbyte.stompreactiveexample.model.entities.Hero;
import it.redbyte.stompreactiveexample.repository.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {
    private final ApplicationEventPublisher eventPublisher;
    private final HeroesRepository heroesRepository;

    @Autowired
    public HeroesService(ApplicationEventPublisher eventPublisher, HeroesRepository heroesRepository) {
        this.eventPublisher = eventPublisher;
        this.heroesRepository = heroesRepository;
    }

    public Mono<Hero> addHero(Hero hero) {
        return heroesRepository.existsById(hero.getId())
                .flatMap(exists -> exists ?
                        Mono.error(new Exception("Hero existing")):
                        heroesRepository.save(hero))
                .doOnSuccess(savedHero -> eventPublisher.publishEvent(new HeroCreatedEvent(savedHero)));
    }

    public Mono<Void> deleteHero(Hero hero) {
        return heroesRepository.delete(hero).doOnSuccess(deletedHero -> eventPublisher.publishEvent(new HeroDeletedEvent(hero)));
    }

    public Mono<Hero> updateHero(Hero hero) {
        return heroesRepository
                .existsById(hero.getId())
                .flatMap(exists -> exists ? heroesRepository.save(hero): Mono.error(new Exception("Already exists")))
                .doOnSuccess(updatedHero -> eventPublisher.publishEvent(new HeroUpdatedEvent(updatedHero)));
    }

    public Mono<Hero> getHero(String id) {
        return heroesRepository.findById(id).switchIfEmpty(Mono.error(new Exception("Not Found")));
    }

    public Flux<Hero> all() {
        return heroesRepository.findAll();
    }

    public Mono<Void> deleteAll() {
        return heroesRepository.deleteAll();
    }
}
