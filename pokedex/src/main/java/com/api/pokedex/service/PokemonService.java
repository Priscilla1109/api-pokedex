package com.api.pokedex.service;

import com.api.pokedex.util.JsonUtil;
import com.api.pokedex.exception.PokemonNotFoundException;
import com.api.pokedex.model.PokemonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class PokemonService {

    private static final String JSON_FOLDER = "/src/main/resources/json/pokemons.json";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public PokemonResponse getPokemonByNameOrNumber(String nameOrNumber) {
        List<PokemonResponse> pokemons;
        try {
            var type = objectMapper.getTypeFactory().constructCollectionType(List.class, PokemonResponse.class);
            pokemons = JsonUtil.loadCollection("json/pokemons.json", type, objectMapper);
        } catch (IOException e) {
            throw new PokemonNotFoundException("Pokemon not found with name or number: " + nameOrNumber);
        }

        try {
            var number = Long.parseLong(nameOrNumber);
            return pokemons.stream()
                    .filter(it -> it.getNumber().equals(number))
                    .findFirst()
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with name or number: " + nameOrNumber));
        } catch (NumberFormatException e) {
            return pokemons.stream()
                    .filter(it -> it.getName().equals(nameOrNumber))
                    .findFirst()
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with name or number: " + nameOrNumber));
        }
    }

//    public Species getSpeciesByName(String name) {
//        try {
//            String speciesJson = loadJsonFromFile(name.toLowerCase());
//            return objectMapper.readValue(speciesJson, Species.class);
//        } catch (IOException e) {
//            throw new PokemonNotFoundException("Species not found with name: " + name);
//        }
//    }
//
//    public EvolutionChain getEvolutionChainById(Long id) {
//        try {
//            String evolutionJson = loadJsonFromFile("evolution_" + id);
//            return objectMapper.readValue(evolutionJson, EvolutionChain.class);
//        } catch (IOException e) {
//            throw new EvolutionChainNotFoundException("Evolution chain not found with id: " + id);
//        }
//    }

}