package it.redbyte.stompreactiveexample.controller;

import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEvent;
import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEventType;
import it.redbyte.stompreactiveexample.event.PosterChangeEvent;
import it.redbyte.stompreactiveexample.model.dto.PosterText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poster")
@CrossOrigin
public class PosterController {
    private String posterText = "Hello, World!";
    private final ApplicationEventPublisher publisher;

    @Autowired
    public PosterController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("")
    public PosterText getText(){
        return PosterText.builder().text(posterText).build();
    }

    @PutMapping("")
    public String setText(@RequestParam String text){
        this.posterText = text;
        publisher.publishEvent(new PosterChangeEvent(PosterText.builder().text(text).build()));
        return text;
    }
}
