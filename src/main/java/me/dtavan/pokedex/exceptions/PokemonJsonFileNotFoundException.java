package me.dtavan.pokedex.exceptions;

public class PokemonJsonFileNotFoundException extends RuntimeException {
    public PokemonJsonFileNotFoundException(String pokemonName) {
        super(pokemonName);
    }
}
