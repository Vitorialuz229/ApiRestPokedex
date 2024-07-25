package ufg.inf.cs.ApiRestPokedex.DTO.pokemon;

import lombok.Getter;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;
import ufg.inf.cs.ApiRestPokedex.entity.Estatistica;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;

@Getter
public class PokemonDTO {

    private final int id;
    private final String apelido;
    private final int nivel;
    private final Pokedex pokedex;
    private final Especie especie;
    private final Estatistica estatistica;
    private final String nivelAmizade;
    private final Treinador treinador;

    public PokemonDTO (int id, String apelido, int nivel, Pokedex pokedex, Especie especie, Estatistica estatistica, String nivelAmizade, Treinador treinador) {
        this.id = id;
        this.apelido = apelido;
        this.nivel = nivel;
        this.pokedex = pokedex;
        this.especie = especie;
        this.estatistica = estatistica;
        this.nivelAmizade = nivelAmizade;
        this.treinador = treinador;
    }
}