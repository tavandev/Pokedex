package me.dtavan.pokedex.bootstrap;


import me.dtavan.pokedex.domain.Pokemon;
import me.dtavan.pokedex.repositories.PokemonNonReactRepository;
import me.dtavan.pokedex.services.JsonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PokemonBootStrap implements CommandLineRunner {

    private final PokemonNonReactRepository repository;
    private final JsonService service;

    public PokemonBootStrap(PokemonNonReactRepository repository, JsonService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void run(String... args) {
        List<Pokemon> pokemonList = Collections.emptyList();
        try {
            pokemonList = service.parseJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.deleteAll();
        repository.saveAll(pokemonList)
                .forEach(System.out::println);

    }
}
