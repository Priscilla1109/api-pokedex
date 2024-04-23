package com.api.pokedex.model;

import lombok.Data;

import java.util.List;

@Data
public class EvolutionChain {
    private int id;
    private List<String> chain;
}
