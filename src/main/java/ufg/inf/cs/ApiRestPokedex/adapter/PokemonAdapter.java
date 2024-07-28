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
    public Pokemon toPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDTO.getId());
        pokemon.setApelido(pokemonDTO.getApelido());
        pokemon.setNivel(pokemonDTO.getNivel());
        pokemon.setNivelAmizade(pokemonDTO.getNivelAmizade());

        if (pokemonDTO.getPokedex() != null) {
            Pokedex pokedex = new Pokedex();
            pokedex.setId(pokemonDTO.getPokedex().getId());
            pokemon.setPokedex(pokemonDTO.getPokedex());
        }

        if (pokemonDTO.getEspecie() != null) {
            Especie especie = new Especie();
            especie.setId(pokemonDTO.getEspecie().getId());
            pokemon.setEspecie(pokemonDTO.getEspecie());
        }

        if (pokemonDTO.getEstatistica() != null) {
            Estatistica estatistica = new Estatistica();
            estatistica.setId(pokemonDTO.getEstatistica().getId());
            pokemon.setEstatistica(pokemonDTO.getEstatistica());
        }

        if (pokemonDTO.getTreinador() != null) {
            Treinador treinador = new Treinador();
            treinador.setId(pokemonDTO.getTreinador().getId());
            pokemon.setTreinador(pokemonDTO.getTreinador());
        }

        return pokemon;
    }

    /**
     * Converte um objeto Pokemon para um objeto PokemonDTO.
     *
     * @param pokemon O objeto Pokemon a ser convertido.
     * @return Um objeto PokemonDTO convertido.
     */
    public PokemonDTO toPokemonDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getApelido(),
                pokemon.getNivel(),
                pokemon.getPokedex(),
                pokemon.getEspecie(),
                pokemon.getEstatistica(),
                pokemon.getNivelAmizade(),
                pokemon.getTreinador()
        );
    }
}
