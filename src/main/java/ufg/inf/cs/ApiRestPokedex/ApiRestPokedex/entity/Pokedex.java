package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Pokedex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = true)
    private Treinador treinador;

    @OneToMany(mappedBy = "pokedex", cascade = CascadeType.ALL)
    private List<Pokemon> pokemons;

}
