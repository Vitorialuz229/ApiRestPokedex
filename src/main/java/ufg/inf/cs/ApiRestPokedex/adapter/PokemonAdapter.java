package ufg.inf.cs.ApiRestPokedex.adapter;

import lombok.Getter;
import lombok.Setter;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.entity.*;

import java.util.List;

@Getter
@Setter
public class PokemonAdapter {
    private List<Pokemon> results;

    /**
     * Converte um objeto PokemonDTO para um objeto Pokemon.
     *
     * @param pokemonDTO O objeto PokemonDTO a ser convertido.
     * @return Um objeto Pokemon convertido.
     */
    public Pokemon toPokemon (PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon ();
        pokemon.setId (pokemonDTO.getId ());
        pokemon.setApelido (pokemonDTO.getApelido ());
        pokemon.setNivel (pokemonDTO.getNivel ());
        pokemon.setNivelAmizade (pokemonDTO.getNivelAmizade ());

        Pokedex pokedex = new Pokedex ();
        Especie especie = new Especie ();
        Estatistica estatistica = new Estatistica ();
        Treinador treinador = new Treinador ();

        pokemon.setPokedex (pokedex);
        pokemon.setEspecie (especie);
        pokemon.setEstatistica (estatistica);
        pokemon.setTreinador (treinador);

        return pokemon;
    }
}
