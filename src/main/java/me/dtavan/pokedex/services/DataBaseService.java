package me.dtavan.pokedex.services;

import me.dtavan.pokedex.domain.Pokemon;
import me.dtavan.pokedex.repositories.PokemonNonReactRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DataBaseService {

    private PokemonNonReactRepository repository;
    private JsonService jsonService;


    public DataBaseService(PokemonNonReactRepository repository, JsonService jsonService) {
        this.repository = repository;
        this.jsonService = jsonService;
    }

    public List<Pokemon> seedDataBase() throws IOException {
        List<Pokemon> pokemonList = jsonService.parseJson();
        return repository.saveAll(pokemonList);

    }
}
