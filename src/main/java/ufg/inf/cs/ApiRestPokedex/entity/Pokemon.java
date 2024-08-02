package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String apelido;

    @Column
    private int nivel;

    @ManyToOne
    @JoinColumn(name = "pokedex_id")
    private Pokedex pokedex;

    @ManyToOne
    @JoinColumn(name = "especie_id", referencedColumnName = "id")
    private Especie especie;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    private Set<TreinadorPokemon> treinadorPokemons = new HashSet<>();

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    private Set<MovimentosPokemon> movimentosPokemon = new HashSet<>();
}