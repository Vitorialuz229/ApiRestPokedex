package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String apelido;

    @Column
    private int nivel;

    @Column
    private int height;

    @Column
    private int weight;

    @Column
    private int baseExperience;

    @Column
    private boolean genero;

    @Column
    private String attack;

    @Column
    private String nivelAmizade;

    @ManyToOne
    @JoinColumn(name = "pokedex_id")
    private Pokedex pokedex;

    @ManyToOne
    @JoinColumn(name = "especie_id", referencedColumnName = "id")
    private Especie especie;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estatistica_id", referencedColumnName = "id")
    private Estatistica estatistica;

    @OneToOne
    @JoinColumn(name = "treinador_id")
    private Treinador treinador;
}
