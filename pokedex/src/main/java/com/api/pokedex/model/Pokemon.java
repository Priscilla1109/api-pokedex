package com.api.pokedex.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Pokemon {
    private Long number;
    private String name;
    private String imageUrl;
    private List<String> types;
    private List<Pokemon> evolutions;
}
