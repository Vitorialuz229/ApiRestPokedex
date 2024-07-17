package ufg.inf.cs.ApiRestPokedex.model.Treinador;

import ufg.inf.cs.ApiRestPokedex.model.Especie.Especie;
import ufg.inf.cs.ApiRestPokedex.model.Mochila.Mochila;
import ufg.inf.cs.ApiRestPokedex.model.Pokemon.Pokemon;

public class Treinador {
    private String name;
    private Mochila mochila;

    public Treinador(String name) {
        this.name = name;
        this.mochila = new Mochila();
    }

   // public void catchPokemon(Especie pokemon) {
  //      mochila.addPokemon(pokemon);
   //     System.out.println(name + " caught " + pokemon.getName());
  //  }

  //  public void showBackpack() {
   //     System.out.println(name + " Mochila: ", mochila.getPokemons());
  //  }

}
