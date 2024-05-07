package com.api.pokedex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {
    private Long number;

    private String name;

    private List<String> type;

    private String imageUrl;

    private List<PokemonResponse> evolutions;
}
