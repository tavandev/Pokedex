package me.dtavan.pokedex.comparators;

import me.dtavan.pokedex.domain.Pokemon;

import java.util.Comparator;

public class PokemonComparator {
    public final static Comparator<Pokemon> byHp = Comparator.comparing(Pokemon::getHp);
    public final static Comparator<Pokemon> byHpDesc = Comparator.comparing(Pokemon::getHp).reversed();
    public final static Comparator<Pokemon> byAttack = Comparator.comparing(Pokemon::getAttack);
    public final static Comparator<Pokemon> byAttackDes = Comparator.comparing(Pokemon::getAttack).reversed();
    public final static Comparator<Pokemon> byDefense = Comparator.comparing(Pokemon::getDefense);
    public final static Comparator<Pokemon> byDefenseDesc = Comparator.comparing(Pokemon::getDefense).reversed();
}
