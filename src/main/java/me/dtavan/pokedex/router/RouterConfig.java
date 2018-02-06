package me.dtavan.pokedex.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<?> routerFunction(RouteHandler routeHandler) {
        return RouterFunctions
                .nest(path("/pokedex/api/"),
                        RouterFunctions
                                .route(RequestPredicates.GET("/all"), routeHandler::all)

                                .andRoute(RequestPredicates.GET("/find/id/{id}"), routeHandler::byId)
                                .andRoute(RequestPredicates.GET("/find/name/{name}"), routeHandler::getByName)
                                .andRoute(RequestPredicates.GET("/find/description/{term}"), routeHandler::getByDescription)
                                .andRoute(RequestPredicates.GET("/find/ability1/{ability}"), routeHandler::getByAbility1)
                                .andRoute(RequestPredicates.GET("/find/ability2/{ability}"), routeHandler::getByAbility2)
                                .andRoute(RequestPredicates.GET("/find/type/{type}"), routeHandler::getByType)

                                .andRoute(RequestPredicates.GET("/sort/hp"), routeHandler::getByHp)
                                .andRoute(RequestPredicates.GET("/sort/hp/desc"), routeHandler::getByHpDesc)
                );
    }
}
