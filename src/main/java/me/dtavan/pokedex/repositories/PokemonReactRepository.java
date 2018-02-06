package me.dtavan.pokedex.repositories;

import me.dtavan.pokedex.domain.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokemonReactRepository extends ReactiveMongoRepository<Pokemon, String> {
    Mono<Pokemon> findByNameIgnoreCase(String name);

    Flux<Pokemon> findByDescriptionContainingIgnoreCase(String description);

    Flux<Pokemon> findByAbility1ContainingIgnoreCase(String ability);

    Flux<Pokemon> findByAbility2ContainingIgnoreCase(String ability);

    Flux<Pokemon> findByType1ContainingIgnoreCase(String type);
}
