package me.dtavan.pokedex.repositories;

import me.dtavan.pokedex.ResourcesForTest;
import me.dtavan.pokedex.domain.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PokemonReactiveRepositoryTest {

    @Autowired
    private PokemonReactRepository repository;

    @Before
    public void setUp() {
//        repository.deleteAll().block();
//        repository.saveAll(ResourcesForTest.fluxPokemons).then().block();
        Flux<Pokemon> pokemonFlux = repository.deleteAll().thenMany(
                ResourcesForTest.fluxPokemons).flatMap(repository::save);

        StepVerifier.create(pokemonFlux)
                .expectNextCount(3L)
                .expectComplete()
                .verify();
    }

    @Test
    public void testSaveAllAsync() {
//        List<Pokemon> pokemonList = repository.findAll().collectList().block();
//
//        assertThat(pokemonList).isNotEmpty();
//        assertThat(pokemonList.size()).isEqualTo(3);

        Flux<Pokemon> pokemonFlux = repository
                .findAll()
                .sort(Comparator.comparing(Pokemon::getName));

        StepVerifier.create(pokemonFlux)
                .expectNext(ResourcesForTest.bulbizarre, ResourcesForTest.florizarre, ResourcesForTest.herbizarre)
                .expectComplete().verify();
        StepVerifier.create(pokemonFlux)
                .expectNextCount(3L)
                .expectComplete()
                .verify();
    }

    @Test
    public void testFindbyIdAsync() {
        Pokemon pokemonToFind = ResourcesForTest.bulbizarre;
        String id = pokemonToFind.getId();
//
//        Pokemon pokemonFound = repository.findById(id).block();
//
//        assertThat(pokemonFound).isNotNull();
//        assertThat(pokemonFound.getName()).isEqualTo("Bulbizarre");
        Mono<Pokemon> pokemonMono = repository.findById(id);
        StepVerifier.create(pokemonMono)
                .expectNextCount(1L)
                .expectComplete()
                .verify();

        StepVerifier.create(pokemonMono)
                .expectNextMatches(pkm -> pkm.getName().equals("Bulbizarre"))
                .expectComplete()
                .verify();
    }

    @Test
    public void testFindByNameIgnoreCaseAsync() {
//        Pokemon pokemonFound1 = repository.findByNameIgnoreCase("Bulbizarre").block();
//        Pokemon pokemonFound2 = repository.findByNameIgnoreCase("bulBIZArRe").block();
        Mono<Pokemon> pokemonFound1 = repository.findByNameIgnoreCase("Bulbizarre");
        Mono<Pokemon> pokemonFound2 = repository.findByNameIgnoreCase("bulBIZArRe");

        StepVerifier.create(pokemonFound1).expectNextMatches(p -> p.getName().equals("Bulbizarre"))
                .expectComplete()
                .verify();
        StepVerifier.create(pokemonFound2).expectNextMatches(p -> p.getName().equals("Bulbizarre"))
                .expectComplete()
                .verify();
//        assertThat(pokemonFound1.getName()).isEqualTo("Bulbizarre");
//        assertThat(pokemonFound2).isEqualTo(pokemonFound1);
    }

    @Test
    public void testFindInDescriptionIgnoreCase() {
//        List<Pokemon> pokemonListContainoinfleur1 = repository.findByDescriptionContainingIgnoreCase("fleur").collectList().block();
//        List<Pokemon> pokemonListContainoinfleur2 = repository.findByDescriptionContainingIgnoreCase("FlEUr").collectList().block();

        Flux<Pokemon> pokemonListContainoinfleur1 = repository.findByDescriptionContainingIgnoreCase("fleur")
                .sort(Comparator.comparing(Pokemon::getName));
        Flux<Pokemon> pokemonListContainoinfleur2 = repository.findByDescriptionContainingIgnoreCase("FlEUr")
                .sort(Comparator.comparing(Pokemon::getName));
        ;

        pokemonListContainoinfleur1.subscribe(System.out::println);
        StepVerifier.create(pokemonListContainoinfleur1)
                .expectNextCount(2L)
                .expectComplete()
                .verify();

        StepVerifier.create(pokemonListContainoinfleur1)
                .expectNextMatches(p -> p.getName().equals("Florizarre"))
                .expectNextMatches(p -> p.getName().equals("Herbizarre"))
                .expectComplete()
                .verify();

        StepVerifier.create(pokemonListContainoinfleur2)
                .expectNextMatches(p -> p.getName().equals("Florizarre"))
                .consumeNextWith(pokemon -> assertThat(pokemon.getName()).isEqualTo("Herbizarre"))
                .expectComplete()
                .verify();

        //assertThat(pokemonListContainoinfleur1.collectList().block()).isEqualTo(pokemonListContainoinfleur2.collectList().block());
//        Assertions.assertThat(pokemonListContainoinfleur1.size()).isEqualTo(2);
//        Assertions.assertThat(pokemonListContainoinfleur1).isEqualTo(pokemonListContainoinfleur2);
    }

    @Test
    public void findByAbility1Containing() {
        List<Pokemon> pokemonListContainingEngrais1 = repository.findByAbility1ContainingIgnoreCase("Engrais2").collectList().block();
        List<Pokemon> pokemonListContainingEngrais2 = repository.findByAbility1ContainingIgnoreCase("enGRais2").collectList().block();

        assertThat(pokemonListContainingEngrais1).isNotEmpty();
        assertThat(pokemonListContainingEngrais2).isNotEmpty();
        assertThat(pokemonListContainingEngrais1.get(0).getName()).isEqualTo("Bulbizarre");
        assertThat(pokemonListContainingEngrais2.get(1).getName()).isEqualTo("Florizarre");
        assertThat(pokemonListContainingEngrais1).isEqualTo(pokemonListContainingEngrais2);
    }

    @Test
    public void findByAbility2ContainingAsync() {
        List<Pokemon> pokemonListContainingChloro1 = repository.findByAbility2ContainingIgnoreCase("Chlorophyle2").collectList().block();
        List<Pokemon> pokemonListContainingChloro2 = repository.findByAbility2ContainingIgnoreCase("chlORophyle2").collectList().block();

        assertThat(pokemonListContainingChloro1).isNotEmpty();
        assertThat(pokemonListContainingChloro2).isNotEmpty();
        assertThat(pokemonListContainingChloro1.size()).isEqualTo(2);
        assertThat(pokemonListContainingChloro1).contains(ResourcesForTest.herbizarre);
        assertThat(pokemonListContainingChloro2).contains(ResourcesForTest.florizarre);
        assertThat(pokemonListContainingChloro1).isEqualTo(pokemonListContainingChloro2);
    }

    @Test
    public void findByType1() {
        List<Pokemon> pokemonList1 = repository.findByType1ContainingIgnoreCase("Plante").collectList().block();
        List<Pokemon> pokemonList2 = repository.findByType1ContainingIgnoreCase("Fleur").collectList().block();
        List<Pokemon> pokemonList3 = repository.findByType1ContainingIgnoreCase("plANtE").collectList().block();

        assertThat(pokemonList1).isNotEmpty();
        assertThat(pokemonList2).isNotEmpty();
        assertThat(pokemonList3).isNotEmpty();

        assertThat(pokemonList1.size()).isEqualTo(2);
        assertThat(pokemonList2.size()).isEqualTo(1);
        assertThat(pokemonList3.size()).isEqualTo(2);

        assertThat(pokemonList1.contains(ResourcesForTest.bulbizarre)).isTrue();
        assertThat(pokemonList2.contains(ResourcesForTest.florizarre)).isTrue();
    }
}