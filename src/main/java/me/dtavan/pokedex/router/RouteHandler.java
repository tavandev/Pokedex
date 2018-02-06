package me.dtavan.pokedex.router;


import me.dtavan.pokedex.domain.Pokemon;
import me.dtavan.pokedex.services.PokemonService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RouteHandler {

    private PokemonService service;

    public RouteHandler(PokemonService service) {
        this.service = service;
    }

    public Mono<ServerResponse> all(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getAll(), Pokemon.class);
    }

    public Mono<ServerResponse> byId(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getById(request.pathVariable("id")), Pokemon.class);
    }

    public Mono<ServerResponse> getByName(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getByName(request.pathVariable("name")), Pokemon.class);
    }

    public Mono<ServerResponse> getByHp(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getByHp(), Pokemon.class);
    }

    public Mono<ServerResponse> getByHpDesc(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getByHpDesc(), Pokemon.class);
    }

    public Mono<ServerResponse> getByDescription(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getByDescription(request.pathVariable("term")), Pokemon.class);
    }

    public Mono<ServerResponse> getByAbility1(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(service.getByAbility1(serverRequest.pathVariable("ability")), Pokemon.class);
    }

    public Mono<ServerResponse> getByAbility2(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(service.getByAbility2(serverRequest.pathVariable("ability")), Pokemon.class);
    }

    public Mono<ServerResponse> getByType(ServerRequest request) {
        return ServerResponse.ok()
                .body(service.getByType(request.pathVariable("type")), Pokemon.class);
    }
}
