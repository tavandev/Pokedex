package me.dtavan.pokedex.services;

import me.dtavan.pokedex.domain.Pokemon;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokemonService {
    Flux<Pokemon> getAll();

    Mono<Pokemon> getById(String id);

    Mono<Pokemon> getByName(String name);

    Flux<Pokemon> getByHp();

    Flux<Pokemon> getByHpDesc();

    Flux<Pokemon> getByDescription(String term);

    Flux<Pokemon> getByAbility1(String ability);

    Flux<Pokemon> getByAbility2(String ability);

    Flux<Pokemon> getByType(String type);
}
