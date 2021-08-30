package it.redbyte.stompreactiveexample;

import it.redbyte.stompreactiveexample.model.entities.Hero;
import it.redbyte.stompreactiveexample.service.HeroesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.UUID;

/**
 * An example application that shows how to create a real-time reactive backend with WebFlux and WebSocket.
 * Roadmap:
 * Repository -> Service -> Events -> EventListener -> ChangeEventMessage --> Controller
 */
@SpringBootApplication
public class StompReactiveExampleApplication {
    private final HeroesService heroesService;
    private final Logger logger = LoggerFactory.getLogger("Initializer");

    @Autowired
    public StompReactiveExampleApplication(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StompReactiveExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return (args) ->
                heroesService.deleteAll()
                        .thenMany(Flux.just("Thor", "Ironman", "Spiderman", "Deadpool")
                                .map(name -> Hero.builder().name(name).rating(new Random().nextInt(10)).id(UUID.randomUUID().toString()).build())
                                .flatMap(heroesService::addHero)
                                .doOnNext(hero -> {
                                    logger.info("Persisted id db: {}", hero);
                                }).doOnError(error -> {
                                    logger.error("Error saving to db.\n {}", error.getMessage());
                                }))
                        .subscribe();
    }
}
