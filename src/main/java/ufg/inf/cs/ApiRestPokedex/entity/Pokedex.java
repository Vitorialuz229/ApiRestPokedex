package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pokedex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Treinador treinador;

    @OneToMany(mappedBy = "pokedex", cascade = CascadeType.ALL)
    private List<Pokemon> pokemons;

}
