package me.dtavan.pokedex.services;

import me.dtavan.pokedex.ResourcesForTest;
import me.dtavan.pokedex.domain.Pokemon;
import me.dtavan.pokedex.repositories.PokemonReactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PokemonServiceTest {
    //
//    @Mock
//    private PokemonNonReactRepository repository;
    @Mock
    private PokemonReactRepository repository;

    @Autowired
    @InjectMocks
    private PokemonService service;


    @Test
    public void getAll() {
        Mockito.when(repository.findAll()).thenReturn(ResourcesForTest.fluxPokemons);

        List<Pokemon> liste = service.getAll().collectList().block();

        assertThat(liste).isNotEmpty();
        assertThat(liste.size()).isEqualTo(3);
        assertThat(liste.get(0).getName()).isEqualTo("Bulbizarre");

        Mockito.verify(repository).findAll();
    }

    @Test
    public void getById() {
        Mockito.when(repository.findById(anyString())).thenReturn(Mono.just(ResourcesForTest.florizarre));

        Pokemon pokemon = service.getById("flo2").block();

        assertThat(pokemon).isNotNull();
        assertThat(pokemon).isInstanceOf(Pokemon.class);
        assertThat(pokemon.getName()).isEqualTo("Florizarre");

        Mockito.verify(repository).findById(anyString());
    }

    @Test
    public void getByName() {
        Mockito.when(repository.findByNameIgnoreCase(anyString())).thenReturn(Mono.just(ResourcesForTest.bulbizarre));

        Pokemon pokemonFound = service.getByName("Bulbizarre").block();

        assertThat(pokemonFound).isNotNull();
        assertThat(pokemonFound).isInstanceOf(Pokemon.class);
        assertThat(pokemonFound.getName()).isEqualTo("Bulbizarre");

        Mockito.verify(repository).findByNameIgnoreCase(anyString());
    }

    @Test
    public void getByHp() {
        Mockito.when(repository.findAll()).thenReturn(ResourcesForTest.fluxPokemons);

        List<Pokemon> sortedList = ResourcesForTest.fluxPokemons.collectList().block()
                .stream().sorted(Comparator.comparing(Pokemon::getHp))
                .collect(Collectors.toList());

        List<Pokemon> listReturned = service.getByHp().collectList().block();


        assertThat(listReturned).isEqualTo(sortedList);
        assertThat(listReturned.get(0).getName()).isEqualTo("Bulbizarre");
        assertThat(listReturned.get(2).getName()).isEqualTo("Florizarre");

        Mockito.verify(repository).findAll();
    }

    @Test
    public void getByHpDesc() {
        Mockito.when(repository.findAll()).thenReturn(ResourcesForTest.fluxPokemons);

        List<Pokemon> sortedList = ResourcesForTest.fluxPokemons.collectList().block()
                .stream().sorted(Comparator.comparing(Pokemon::getHp)
                        .reversed())
                .collect(Collectors.toList());

        List<Pokemon> listReturned = service.getByHpDesc().collectList().block();

        assertThat(listReturned).isEqualTo(sortedList);
        assertThat(listReturned.get(0).getName()).isEqualTo("Florizarre");
        assertThat(listReturned.get(2).getName()).isEqualTo("Bulbizarre");

        Mockito.verify(repository).findAll();
    }

    @Test
    public void getByDescription() {

        Mockito.when(repository.findByDescriptionContainingIgnoreCase(anyString()))
                .thenReturn(Flux.just(ResourcesForTest.bulbizarre, ResourcesForTest.herbizarre));

        List<Pokemon> liste = service.getByDescription("fleur").collectList().block();

        assertThat(liste.size()).isEqualTo(2);
        assertThat(liste.get(0).getName()).isEqualTo("Bulbizarre");
        assertThat(liste.get(1).getName()).isEqualTo("Herbizarre");

        Mockito.verify(repository).findByDescriptionContainingIgnoreCase(anyString());
    }

    @Test
    public void getByAbility1() {
        Mockito.when(repository.findByAbility1ContainingIgnoreCase(anyString()))
                .thenReturn(Flux.just(ResourcesForTest.bulbizarre, ResourcesForTest.herbizarre, ResourcesForTest.florizarre));

        List<Pokemon> pokemonList = service.getByAbility1("Engrais").collectList().block();

        assertThat(pokemonList).isNotEmpty();
        assertThat(pokemonList.size()).isEqualTo(3);

        Mockito.verify(repository).findByAbility1ContainingIgnoreCase(anyString());
    }

    @Test
    public void getByAbility2() {
        Mockito.when(repository.findByAbility2ContainingIgnoreCase(anyString()))
                .thenReturn(Flux.just(ResourcesForTest.bulbizarre, ResourcesForTest.herbizarre, ResourcesForTest.florizarre));

        List<Pokemon> pokemonList = service.getByAbility2("Végétal").collectList().block();

        assertThat(pokemonList).isNotEmpty();
        assertThat(pokemonList.size()).isEqualTo(3);

        Mockito.verify(repository).findByAbility2ContainingIgnoreCase(anyString());
    }

    @Test
    public void testGetByType() {
        Mockito.when(repository.findByType1ContainingIgnoreCase(anyString())).thenReturn(ResourcesForTest.fluxPokemons);

        List<Pokemon> pokemonList = service.getByType("Feu").collectList().block();

        assertThat(pokemonList).isNotEmpty();
        assertThat(pokemonList.size()).isEqualTo(3);

        Mockito.verify(repository).findByType1ContainingIgnoreCase(anyString());
    }
}