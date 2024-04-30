package com.api.pokedex.service;

import com.api.pokedex.model.EvolutionChain;
import com.api.pokedex.model.Pokemon;
import com.api.pokedex.model.PokemonPageResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class PokeApiService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${pokeapi.baseurl}")
    private String baseUrl;

    public PokeApiService() {}

    public Pokemon getPokemonByName(String name) {
        String url = baseUrl + "/pokemon/" + name;
        ResponseEntity<Pokemon> response = restTemplate.getForEntity(url, Pokemon.class);
        return response.getBody();
    }

    public EvolutionChain getEvolutionChainByNumber(Long number) {
        String url = baseUrl + "/evolution-chain/" + number;
        ResponseEntity<EvolutionChain> response = restTemplate.getForEntity(url, EvolutionChain.class);
        return response.getBody();
    }

    public PokemonPageResponse listPokemons(int page, int pageSize) {
        String url = baseUrl + "/pokemons?page=" + page + "&pageSize=" + pageSize;
        ResponseEntity<PokemonPageResponse> response = restTemplate.getForEntity(url, PokemonPageResponse.class);
        return response.getBody();
    }
}

