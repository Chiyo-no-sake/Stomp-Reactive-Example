package it.redbyte.stompreactiveexample.model.entities;

import it.redbyte.stompreactiveexample.model.dto.HeroRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
@Builder
public class Hero {
    @Id
    private String id;
    private String name;
    private int rating;

    public static Hero fromRequest(HeroRequest request){
        return Hero.builder()
                .id(UUID.randomUUID().toString())
                .rating(request.getRating())
                .name(request.getName())
                .build();
    }
}
