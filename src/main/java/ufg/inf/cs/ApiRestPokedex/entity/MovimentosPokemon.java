package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ufg.inf.cs.ApiRestPokedex.entity.Movimentos;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;

@Entity
@Getter
@Setter
public class MovimentosPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "movimento_id")
    private Movimentos movimentos;

    @Column
    private int level;
}