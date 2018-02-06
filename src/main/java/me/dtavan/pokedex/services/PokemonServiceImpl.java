package me.dtavan.pokedex.services;

import me.dtavan.pokedex.comparators.PokemonComparator;
import me.dtavan.pokedex.domain.Pokemon;
import me.dtavan.pokedex.repositories.PokemonReactRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokemonServiceImpl implements PokemonService {

    //private PokemonNonReactRepository nonReactRepository;
    private PokemonReactRepository repository;

//    public PokemonServiceImpl(PokemonNonReactRepository nonReactRepository) {
//        this.nonReactRepository = nonReactRepository;
//    }


    public PokemonServiceImpl(PokemonReactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Pokemon> getAll() {
        return repository.findAll();
        //return Flux.fromIterable(nonReactRepository.findAll());
    }

    @Override
    public Mono<Pokemon> getById(String id) {
        return repository.findById(id);
        //return Mono.just(nonReactRepository.findById(id).get());
    }

    @Override
    public Mono<Pokemon> getByName(String name) {
        return repository.findByNameIgnoreCase(name);
        //return Mono.just(nonReactRepository.findByNameIgnoreCase(name));
    }

    @Override
    public Flux<Pokemon> getByHp() {
        return repository.findAll().sort(PokemonComparator.byHp);
//        List<Pokemon> liste = nonReactRepository.findAll().stream().sorted(PokemonComparator.byHp).collect(Collectors.toList());
//        return Flux.fromIterable(liste);
    }

    @Override
    public Flux<Pokemon> getByHpDesc() {
        return repository.findAll().sort(PokemonComparator.byHpDesc);
//        List<Pokemon> liste = nonReactRepository.findAll().stream().sorted(PokemonComparator.byHpDesc).collect(Collectors.toList());
//        return Flux.fromIterable(liste);
    }

    @Override
    public Flux<Pokemon> getByDescription(String term) {
        return repository.findByDescriptionContainingIgnoreCase(term);
        //return Flux.fromIterable(nonReactRepository.findByDescriptionContainingIgnoreCase(term));
    }

    @Override
    public Flux<Pokemon> getByAbility1(String ability) {
        return repository.findByAbility1ContainingIgnoreCase(ability);
        //return Flux.fromIterable(nonReactRepository.findByAbility1ContainingIgnoreCase(ability));
    }

    @Override
    public Flux<Pokemon> getByAbility2(String ability) {
        return repository.findByAbility2ContainingIgnoreCase(ability);
        //return Flux.fromIterable(nonReactRepository.findByAbility2ContainingIgnoreCase(ability));
    }

    @Override
    public Flux<Pokemon> getByType(String type) {
        return repository.findByType1ContainingIgnoreCase(type);
    }
}
