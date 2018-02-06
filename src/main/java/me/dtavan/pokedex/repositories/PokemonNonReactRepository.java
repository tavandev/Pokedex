package me.dtavan.pokedex.repositories;


import me.dtavan.pokedex.domain.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PokemonNonReactRepository extends MongoRepository<Pokemon, String> {
    Pokemon findByNameIgnoreCase(String name);

    List<Pokemon> findByDescriptionContainingIgnoreCase(String description);

    List<Pokemon> findByAbility1ContainingIgnoreCase(String ability);

    List<Pokemon> findByAbility2ContainingIgnoreCase(String ability);

    List<Pokemon> findByType1ContainingIgnoreCase(String type);
}
