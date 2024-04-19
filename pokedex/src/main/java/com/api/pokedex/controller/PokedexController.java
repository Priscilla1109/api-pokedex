package com.api.pokedex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PokedexController {

    private final Map<Integer, String> pokemons = Map.of(
            1, "bulbasaur"
    );

    @GetMapping("/api/v2/pokemon/{name}")
    public String getPokemonByName(@PathVariable("name") String name) {
        for (Map.Entry<Integer, String> entry : pokemons.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(name)) {
                return entry.getValue();
            }
        }
        return "Pokemon not found";
    }

    @GetMapping("/api/v2/pokemon/{number}")
    public String getPokemonByNumber(@PathVariable("number") Integer number) {
        if (pokemons.containsKey(number)) {
            return pokemons.get(number);
        } else {
            return "Pokemon not found";
        }
    }
}