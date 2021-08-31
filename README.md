# Live Web Socket
Simple classes collection to implement live list updates via websocket and http between Spring Boot and Angular.

## For Server
Dependencies:
* spring-boot-starter-websocket
* lombok

1) Copy classes inside ```it.redbyte.stompreactiveexample.config``` into your project
2) Customize the class ```WebSocketConfig```
3) Create a class extending ```ChangeEventListenerConfigurer```, with ```@Configuration```. Like this example:

```java
@Configuration
public class ChangeEventListener extends ChangeEventListenerConfigurer {

    @Autowired
    public ChangeEventListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }

    @Override
    public void configureChangeEventMappings(Map<Class<? extends ChangeEvent>, String> changeEventMappings) {
        changeEventMappings.put(HeroChangeEvent.class, "/topic/heroes/updates");
    }
}
```

4) Create your event classes extending ```ChangeEvent```, configure their mappings in the above class
5) Publish events with an injected ```ApplicationEventListener``` from spring, for example in a service you may have:

```java
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
```

## For Client
Dependencies:
* ng2-stompjs

1) Copy the module directory ```live-web-socket``` in your project
2) Customize ```rx-stomp.config.ts``` with the correct ws address
3) Request the service ```LiveWebSocketService```, then use its methods to retrieve observables like the following:

```typescript
import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Hero} from "./live-web-socket/live-web-socket.service";
import {LiveRestService} from "./live-web-socket/live-rest.service";

@Component({
    selector: 'app-root',
    template: `<p *ngFor="let hero of heroes | async">
                {{hero.name}}, rating: {{hero.rating}}
             </p>`,
    styles: []
})
export class AppComponent implements OnInit {
    heroes?: Observable<Hero[]>;

    constructor(private restService: LiveRestService) {
    }

    ngOnInit(): void {
        this.heroes = this.restService.liveList<Hero>('http://localhost:8080/heroes', '/topic/heroes/updates');
    }

}
```