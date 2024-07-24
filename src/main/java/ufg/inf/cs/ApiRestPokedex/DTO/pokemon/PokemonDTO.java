package ufg.inf.cs.ApiRestPokedex.DTO.pokemon;

import ufg.inf.cs.ApiRestPokedex.entity.Especie;
import ufg.inf.cs.ApiRestPokedex.entity.Estatistica;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;

public class PokemonDTO {

    private int id;
    private String apelido;
    private int nivel;
    private Pokedex pokedex;
    private Especie especie;
    private Estatistica estatistica;
    private String nivelAmizade;
    private Treinador treinador;

    public PokemonDTO(int id, String apelido, int nivel, Pokedex pokedex, Especie especie, Estatistica estatistica, String nivelAmizade, Treinador treinador) {
        this.id = id;
        this.apelido = apelido;
        this.nivel = nivel;
        this.pokedex = pokedex;
        this.especie = especie;
        this.estatistica = estatistica;
        this.nivelAmizade = nivelAmizade;
        this.treinador = treinador;
    }

    public int getId() {
        return id;
    }

    public String getApelido() {
        return apelido;
    }

    public int getNivel() {
        return nivel;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Estatistica getEstatistica() {
        return estatistica;
    }

    public String getNivelAmizade() {
        return nivelAmizade;
    }

    public Treinador getTreinador() {
        return treinador;
    }
}
