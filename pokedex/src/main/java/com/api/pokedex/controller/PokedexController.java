package com.api.pokedex.controller;

import com.api.pokedex.model.*;
import com.api.pokedex.service.PokeApiService;
import com.api.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api-pokedex/v2")
public class PokedexController {
    @Autowired
    private PokeApiService pokeApiService;

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/pokemons")
    public ResponseEntity<PokemonPageResponse> listPokemons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        PokemonPageResponse pokemonPageResponse = pokeApiService.listPokemons(page, pageSize);
        return ResponseEntity.ok(pokemonPageResponse);
    }

    @GetMapping("/pokemon/{nameOrNumber}")
    public ResponseEntity<PokemonResponse> getPokemonByNameOrNumber(@PathVariable String nameOrNumber) {
        PokemonResponse pokemon = pokemonService.getPokemonByNameOrNumber(nameOrNumber);
        return ResponseEntity.ok(pokemon);
    }
}