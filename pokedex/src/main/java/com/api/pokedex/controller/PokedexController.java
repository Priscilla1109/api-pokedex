package com.api.pokedex.controller;

import com.api.pokedex.model.EvolutionChain;
import com.api.pokedex.model.Pokemon;
import com.api.pokedex.model.PokemonPageResponse;
import com.api.pokedex.service.PokeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pokedex")
public class PokedexController {
    @Autowired
    private PokeApiService pokeApiService;

    @GetMapping("/pokemon/{name}")
    public ResponseEntity<Pokemon> getPokemonByName(@PathVariable("name") String name) {
        Pokemon pokemon = pokeApiService.getPokemonByName(name);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/evolution-chain/{id}")
    public ResponseEntity<EvolutionChain> getPokemonByNumber(@PathVariable("number") Long number) {
        EvolutionChain evolutionChain = pokeApiService.getEvolutionChainByNumber(number);
        return ResponseEntity.ok(evolutionChain);
    }

    @GetMapping("/pokemons")
    public ResponseEntity<PokemonPageResponse> listPokemons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        PokemonPageResponse pokemonPageResponse = pokeApiService.listPokemons(page, pageSize);
        return ResponseEntity.ok(pokemonPageResponse);
    }
}