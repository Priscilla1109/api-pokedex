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
    @Autowired
    private ObjectMapper objectMapper;

    public PokemonResponse getPokemonByNameOrNumber(String nameOrNumber) {
        List<PokemonResponse> pokemons;
        try {
            // Carrega a lista de Pokémons a partir do arquivo JSON
            var type = objectMapper.getTypeFactory().constructCollectionType(List.class, PokemonResponse.class);
            pokemons = JsonUtil.loadCollection("json/pokemons.json", type, objectMapper);
        } catch (IOException e) {
            // Se houver um erro ao carregar os Pokémons, lança uma exceção
            throw new PokemonNotFoundException("Failed to load Pokémon data");
        }

        // Tenta converter o nomeOrNumber para número
        try {
            long number = Long.parseLong(nameOrNumber);
            // Se conseguiu converter para número, busca pelo número do Pokémon
            return pokemons.stream()
                    .filter(pokemon -> pokemon.getNumber() == number)
                    .findFirst()
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with number: " + number));
        } catch (NumberFormatException e) {
            // Se não conseguiu converter para número, busca pelo nome do Pokémon
            return pokemons.stream()
                    .filter(pokemon -> pokemon.getName().equalsIgnoreCase(nameOrNumber))
                    .findFirst()
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with name: " + nameOrNumber));
        }
    }
}