package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @ElementCollection
    private List<String> tipos;
    private int peso;
    private int altura;

    @ManyToOne
    @JoinColumn(name = "pokedex_id")
    private Pokedex pokedex;

    @ManyToOne
    @JoinColumn(name = "especie_id", referencedColumnName = "id")
    private Especie especie;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estatistica_id", referencedColumnName = "id")
    private Estatistica estatistica;

    private String nivelAmizade; // Relacionado com o treinador

    @ManyToOne
    @JoinColumn(name = "treinador_id")
    private Treinador treinador;
}
