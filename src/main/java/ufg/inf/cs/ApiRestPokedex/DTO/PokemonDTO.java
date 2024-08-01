package ufg.inf.cs.ApiRestPokedex.DTO;

import lombok.Getter;
import lombok.Setter;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;
import ufg.inf.cs.ApiRestPokedex.entity.Estatistica;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;

@Getter
@Setter
public class PokemonDTO {

    private Long id;
    private String apelido;
    private int nivel;
    private Especie especie;
    private Estatistica estatistica;
    private String nivelAmizade;
    private Pokedex pokedex;

    public PokemonDTO(Long id, String apelido, int nivel, Especie especie, Estatistica estatistica, String nivelAmizade, Pokedex pokedex) {
        this.id = id;
        this.apelido = apelido;
        this.nivel = nivel;
        this.especie = especie;
        this.estatistica = estatistica;
        this.nivelAmizade = nivelAmizade;
        this.pokedex = pokedex;
    }
}
