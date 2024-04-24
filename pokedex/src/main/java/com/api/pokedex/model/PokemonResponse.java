package com.api.pokedex.model;

import lombok.Data;

@Data
public class PokemonResponse {
    private Long number;
    private String name;
    private String type;
    private String imageUrl;
}
