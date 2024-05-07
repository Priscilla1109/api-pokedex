package com.api.pokedex.controller;

import com.api.pokedex.model.*;
import com.api.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-pokedex/v2")
public class PokedexController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/pokemon/{nameOrNumber}")
    public ResponseEntity<PokemonResponse> getPokemonByNameOrNumber(@PathVariable String nameOrNumber) {
        PokemonResponse pokemon = pokemonService.getPokemonByNameOrNumber(nameOrNumber);
        return ResponseEntity.ok(pokemon);
    }
}