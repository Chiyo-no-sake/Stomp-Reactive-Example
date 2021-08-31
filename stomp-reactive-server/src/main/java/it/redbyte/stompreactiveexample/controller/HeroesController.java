package it.redbyte.stompreactiveexample.controller;

import it.redbyte.stompreactiveexample.model.dto.HeroRequest;
import it.redbyte.stompreactiveexample.model.entities.Hero;
import it.redbyte.stompreactiveexample.service.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/heroes")
public class HeroesController {
    private final HeroesService heroesService;

    @Autowired
    public HeroesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping("")
    public Flux<Hero> all() {
        return heroesService.all();
    }

    @GetMapping("{id}")
    public Mono<Hero> hero(@PathVariable String id) {
        return heroesService.getHero(id);
    }

    @PostMapping("new")
    public Mono<Hero> create(@RequestBody HeroRequest request){
        return heroesService.addHero(Hero.fromRequest(request));
    }

    @PutMapping("{id}")
    public Mono<Hero> update(@RequestBody HeroRequest request, @PathVariable String id){
        Hero hero = Hero.fromRequest(request);
        hero.setId(id);
        return heroesService.updateHero(hero);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable String id){
        return heroesService.getHero(id)
                .flatMap(heroesService::deleteHero);
    }
}
