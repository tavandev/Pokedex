package me.dtavan.pokedex.ittests;

import me.dtavan.pokedex.domain.Pokemon;
import me.dtavan.pokedex.repositories.PokemonNonReactRepository;
import me.dtavan.pokedex.repositories.PokemonReactRepository;
import me.dtavan.pokedex.router.RouteHandler;
import me.dtavan.pokedex.router.RouterConfig;
import me.dtavan.pokedex.services.JsonService;
import me.dtavan.pokedex.services.PokemonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationRouterTest {

    private WebTestClient client;


    private RouteHandler routeHandler;

    @Autowired
    private PokemonService service;


    @Autowired
    private JsonService jsonService;

    @Autowired
    private PokemonNonReactRepository repository;

    private List pokemonList = Collections.EMPTY_LIST;

    @Autowired
    private PokemonReactRepository repository2;

    @Before
    public void setUp() throws Exception {
        routeHandler = new RouteHandler(service);

        RouterConfig routerConfig = new RouterConfig();

        RouterFunction<?> routerFunction = routerConfig.routerFunction(routeHandler);
        client = WebTestClient.bindToRouterFunction(routerFunction).build();

        repository.deleteAll();
        pokemonList = repository.saveAll(jsonService.parseJson());
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testLong() throws IOException {
        List<Pokemon> pokemonList = jsonService.parseJson();
        assertThat(pokemonList.size()).isEqualTo(386);

        Flux<Pokemon> pokemonFlux = repository2.findAll();

        StepVerifier.create(pokemonFlux).expectNextCount(386L).expectComplete().verify();
        StepVerifier.create(pokemonFlux).expectNextSequence(pokemonList).expectComplete().verify();
    }

    @Test
    public void testITSeedDatabase() {
        assertThat(pokemonList).isNotEmpty();
        assertThat(pokemonList.size()).isEqualTo(386);
    }

    @Test
    public void testITRouteAllPokemons() {
        client.get().uri("/pokedex/api/all")
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().jsonPath("$[0].name").isEqualTo("Bulbizarre");
    }

    @Test
    public void testITRouteFindById() {
        client.get().uri("/pokedex/api/find/id/{id}", "99")
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().jsonPath("$.name").isEqualTo("Krabboss");
    }

    @Test
    public void testITRouteFindByNameIgnoreCase() {
        client.get().uri("/pokedex/api/find/name/{name}", "Dracaufeu")
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectStatus().isOk()
                .expectBody().jsonPath("$.name").isEqualTo("Dracaufeu");

        client.get().uri("/pokedex/api/find/name/{name}", "draCAUfEu")
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectStatus().isOk()
                .expectBody().jsonPath("$.name").isEqualTo("Dracaufeu");
    }

    @Test
    public void testITFindByDescriptionIgnoreCase() {
        client.get().uri("/pokedex/api/find/description/{term}", "fleur")
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Herbizarre")
                .jsonPath("$[3].name").isEqualTo("Meganium");

        client.get().uri("/pokedex/api/find/description/{term}", "FleUR")
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Herbizarre")
                .jsonPath("$[3].name").isEqualTo("Meganium");

    }

    @Test
    public void testITSortHp() {
        client.get().uri("/pokedex/api/sort/hp")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Munja")
                .jsonPath("$[385].name").isEqualTo("Leuphorie");
    }

    @Test
    public void testITSortHpDesc() {
        client.get().uri("/pokedex/api/sort/hp/desc")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Leuphorie")
                .jsonPath("$[385].name").isEqualTo("Munja");
    }

    @Test
    public void testITFindByAbality() {
        client.get().uri("/pokedex/api/find/ability1/{ability}", "Engrais")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Bulbizarre")
                .jsonPath("$[8].name").isEqualTo("Jungko");
    }

    @Test
    public void testITFindByAbality2() {
        client.get().uri("/pokedex/api/find/ability2/{ability}", "Cielgris")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Psykokwak");
    }

    @Test
    public void testITFindByType1() {
        client.get().uri("/pokedex/api/find/type/{type}", "Feu")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Salameche")
                .jsonPath("$[4].name").isEqualTo("Feunard");
    }
}
