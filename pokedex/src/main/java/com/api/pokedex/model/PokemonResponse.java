package com.api.pokedex.model;

import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {
    private Long number;
    private String name;
    private List<String> type;
    private String imageUrl;
}
