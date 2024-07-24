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

        Pokedex pokedex = new Pokedex();
        Especie especie = new Especie();
        Estatistica estatistica = new Estatistica();
        Treinador treinador = new Treinador();

        pokemon.setPokedex(pokedex);
        pokemon.setEspecie(especie);
        pokemon.setEstatistica(estatistica);
        pokemon.setTreinador(treinador);

        return pokemon;
    }

    /**
     * Converte um objeto Pokemon para um objeto PokemonDTO.
     *
     * @param pokemon O objeto Pokemon a ser convertido.
     * @return Um objeto PokemonDTO convertido.
     */
   /* public PokemonDTO toPokemonDTO(Pokemon pokemon) {
        return PokemonDTO.builder()
                .id(pokemon.getId())
                .apelido(pokemon.getApelido())
                .nivel(pokemon.getNivel())
                .nivelAmizade(pokemon.getNivelAmizade())
                .pokedexId(pokemon.getPokedex() != null ? pokemon.getPokedex().getId() : null)
                .especieId(pokemon.getEspecie() != null ? pokemon.getEspecie().getId() : null)
                .estatisticaId(pokemon.getEstatistica() != null ? pokemon.getEstatistica().getId() : null)
                .treinadorId(pokemon.getTreinador() != null ? pokemon.getTreinador().getId() : null)
                .build();
    }*/
}
