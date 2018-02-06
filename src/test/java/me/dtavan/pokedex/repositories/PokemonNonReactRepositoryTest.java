package me.dtavan.pokedex.repositories;

import me.dtavan.pokedex.ResourcesForTest;
import me.dtavan.pokedex.domain.Pokemon;
import org.assertj.core.api.Java6Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PokemonNonReactRepositoryTest {


    @Autowired
    private PokemonNonReactRepository repository;

    private List<Pokemon> listSaved = Collections.emptyList();

    @Before
    public void setUp() {
        repository.deleteAll();
        this.listSaved = repository.saveAll(ResourcesForTest.fluxPokemons.collectList().block());
    }

    @Test
    public void testSaveAll() {
        assertThat(listSaved).isNotEmpty();
        assertThat(listSaved.size()).isEqualTo(3);
    }

    @Test
    public void testSaveOne() {
        Pokemon pokemon = new Pokemon();
        pokemon.setId("abcd");

        repository.save(pokemon);
        Pokemon pokemon1 = repository.findById("abcd").get();

        assertThat(pokemon1.getName()).isEqualTo(pokemon.getName());
    }

    @Test
    public void testFindById() {
        Optional<Pokemon> pokemonFound = repository.findById(ResourcesForTest.bulbizarre.getId());

        assertThat(pokemonFound.get().getName()).isEqualTo("Bulbizarre");
    }

    @Test
    public void testFindByNameIgnoreCase() {
        Pokemon pokemonFound1 = repository.findByNameIgnoreCase("Bulbizarre");
        Pokemon pokemonFound2 = repository.findByNameIgnoreCase("bulBIzaRRe");

        assertThat(pokemonFound1).isEqualTo(pokemonFound2);
    }

    @Test
    public void testFindInDescriptionIgnoreCase() {
        List<Pokemon> pokemonListContainoinfleur1 = repository.findByDescriptionContainingIgnoreCase("fleur");
        List<Pokemon> pokemonListContainoinfleur2 = repository.findByDescriptionContainingIgnoreCase("FlEUr");

        assertThat(pokemonListContainoinfleur1.size()).isEqualTo(2);
        assertThat(pokemonListContainoinfleur1).isEqualTo(pokemonListContainoinfleur2);
    }

    @Test
    public void findByAbility1Containing() {
        List<Pokemon> pokemonListContainingEngrais1 = repository.findByAbility1ContainingIgnoreCase("Engrais2");
        List<Pokemon> pokemonListContainingEngrais2 = repository.findByAbility1ContainingIgnoreCase("enGRais2");

        assertThat(pokemonListContainingEngrais1).isNotEmpty();
        assertThat(pokemonListContainingEngrais2).isNotEmpty();
        assertThat(pokemonListContainingEngrais1.get(0).getName()).isEqualTo("Bulbizarre");
        assertThat(pokemonListContainingEngrais2.get(1).getName()).isEqualTo("Florizarre");
        assertThat(pokemonListContainingEngrais1).isEqualTo(pokemonListContainingEngrais2);
    }

    @Test
    public void findByAbility2Containing() {
        List<Pokemon> pokemonListContainingChloro1 = repository.findByAbility2ContainingIgnoreCase("Chlorophyle2");
        List<Pokemon> pokemonListContainingChloro2 = repository.findByAbility2ContainingIgnoreCase("chlORophyle2");

        assertThat(pokemonListContainingChloro1).isNotEmpty();
        assertThat(pokemonListContainingChloro2).isNotEmpty();
        assertThat(pokemonListContainingChloro1.get(0).getName()).isEqualTo("Herbizarre");
        assertThat(pokemonListContainingChloro2.get(1).getName()).isEqualTo("Florizarre");
        assertThat(pokemonListContainingChloro1).isEqualTo(pokemonListContainingChloro2);
    }

    @Test
    public void findByType1() {
        List<Pokemon> pokemonList1 = repository.findByType1ContainingIgnoreCase("Plante");
        List<Pokemon> pokemonList2 = repository.findByType1ContainingIgnoreCase("Fleur");
        List<Pokemon> pokemonList3 = repository.findByType1ContainingIgnoreCase("plANtE");

        Java6Assertions.assertThat(pokemonList1).isNotEmpty();
        Java6Assertions.assertThat(pokemonList2).isNotEmpty();
        Java6Assertions.assertThat(pokemonList3).isNotEmpty();

        Java6Assertions.assertThat(pokemonList1.size()).isEqualTo(2);
        Java6Assertions.assertThat(pokemonList2.size()).isEqualTo(1);
        Java6Assertions.assertThat(pokemonList3.size()).isEqualTo(2);

        Java6Assertions.assertThat(pokemonList1.contains(ResourcesForTest.bulbizarre)).isTrue();
        Java6Assertions.assertThat(pokemonList2.contains(ResourcesForTest.florizarre)).isTrue();
    }
}
