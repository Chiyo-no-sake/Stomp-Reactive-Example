package it.redbyte.stompreactiveexample.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeroRequest {
    String name;
    int rating;
}
