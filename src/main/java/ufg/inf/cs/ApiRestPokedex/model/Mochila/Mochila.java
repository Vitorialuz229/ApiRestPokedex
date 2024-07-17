package ufg.inf.cs.ApiRestPokedex.model.Mochila;

import ufg.inf.cs.ApiRestPokedex.model.Especie.Especie;
import ufg.inf.cs.ApiRestPokedex.model.Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Mochila {
    private List<Pokemon> pokemons;

    public Mochila() {
        this.pokemons = new ArrayList<>();
    }

   // public void addPokemon(Especie especie) {
    //    pokemons.add(especie);
    //}

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
