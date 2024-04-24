package com.api.pokedex.service;

import com.api.pokedex.exception.EvolutionChainNotFoundException;
import com.api.pokedex.exception.PokemonNotFoundException;
import com.api.pokedex.model.EvolutionChain;
import com.api.pokedex.model.PokemonResponse;
import com.api.pokedex.model.Species;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PokemonService {

    private static final String JSON_FOLDER = "src/main/resources/json/pokemon/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public PokemonResponse getPokemonByNameOrNumber(String nameOrNumber) {
        try {
            String pokemonJson = loadJsonFromFile(nameOrNumber.toLowerCase());
            return objectMapper.readValue(pokemonJson, PokemonResponse.class);
        } catch (IOException e) {
            throw new PokemonNotFoundException("Pokemon not found with name or number: " + nameOrNumber);
        }
    }

    public Species getSpeciesByName(String name) {
        try {
            String speciesJson = loadJsonFromFile(name.toLowerCase());
            return objectMapper.readValue(speciesJson, Species.class);
        } catch (IOException e) {
            throw new PokemonNotFoundException("Species not found with name: " + name);
        }
    }

    public EvolutionChain getEvolutionChainById(Long id) {
        try {
            String evolutionJson = loadJsonFromFile("evolution_" + id);
            return objectMapper.readValue(evolutionJson, EvolutionChain.class);
        } catch (IOException e) {
            throw new EvolutionChainNotFoundException("Evolution chain not found with id: " + id);
        }
    }

    private String loadJsonFromFile(String fileName) throws IOException {
        Path path = Paths.get(JSON_FOLDER + fileName + ".json");
        return new String(Files.readAllBytes(path));
    }
}