package me.dtavan.pokedex.router;


import me.dtavan.pokedex.ResourcesForTest;
import me.dtavan.pokedex.services.PokemonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@WebFluxTest
public class RouterConfigTest {

    private WebTestClient client;

    @Mock
    private PokemonService service;

    @Mock
    private RouteHandler routeHandler;

    @Before
    public void setUp() {
        routeHandler = new RouteHandler(service);
        RouterConfig routerConfig = new RouterConfig();
        RouterFunction<?> routerFunction = routerConfig.routerFunction(routeHandler);
        client = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    public void routeAll() {
        Mockito.when(service.getAll()).thenReturn(ResourcesForTest.fluxPokemons);

        client.get().uri("/pokedex/api/all")
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(service).getAll();
    }

    @Test
    public void routeById() {
        Mockito.when(service.getById(anyString())).thenReturn(Mono.just(ResourcesForTest.herbizarre));

        client.get().uri("/pokedex/api/find/id/{id}", "un")
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(service).getById(anyString());
    }

    @Test
    public void routeFindByName() {
        Mockito.when(service.getByName(anyString())).thenReturn(Mono.just(ResourcesForTest.bulbizarre));

        client.get().uri("/pokedex/api/find/name/{name}", "name")
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(service).getByName(anyString());
    }

    @Test
    public void testFindPokemonByDescription() {
        Mockito.when(service.getByDescription(anyString())).thenReturn(ResourcesForTest.fluxPokemons);
        client.get().uri("/pokedex/api/find/description/{term}", "term")
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(service).getByDescription(anyString());

    }

    @Test
    public void testFindByAbility() {
        Mockito.when(service.getByAbility1(anyString())).thenReturn(ResourcesForTest.fluxPokemons);
        client.get().uri("/pokedex/api/find/ability1/{ability}", "Engrais")
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(service).getByAbility1(anyString());
    }

    @Test
    public void testFindByAbility2() {
        Mockito.when(service.getByAbility2(anyString())).thenReturn(ResourcesForTest.fluxPokemons);
        client.get().uri("/pokedex/api/find/ability2/{ability}", "Végétal")
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(service).getByAbility2(anyString());
    }

    @Test
    public void testByType() {
        Mockito.when(service.getByType(anyString())).thenReturn(ResourcesForTest.fluxPokemons);
        client.get().uri("/pokedex/api/find/type/{type}", "feu")
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(service).getByType(anyString());
    }

    @Test
    public void testSortByHp() {
        Mockito.when(service.getByHp()).thenReturn(ResourcesForTest.fluxPokemons);

        client.get().uri("/pokedex/api/sort/hp")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service).getByHp();
    }

    @Test
    public void testSortByHpDesc() {
        Mockito.when(service.getByHpDesc()).thenReturn(ResourcesForTest.fluxPokemons);

        client.get().uri("/pokedex/api/sort/hp/desc")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service).getByHpDesc();
    }

}