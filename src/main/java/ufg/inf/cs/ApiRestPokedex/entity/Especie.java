package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String types;

    @Column
    private String variation;

    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pokemon> pokemons;

    public String getUrl() {
        return null;
    }
    @ManyToMany
    @JoinTable(
            name = "especie_tipo",
            joinColumns = @JoinColumn(name = "especie_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id")
    )
    private List<Tipos> tipos;

    @Column
    private int weight;

    @Column
    private int height;

    @Column
    private int base_experience;

    @ManyToMany
    @JoinTable(
            name = "especie_movimentos",
            joinColumns = @JoinColumn(name = "especie_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id")
    )
    private List<Movimentos> movimentos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estatistica_id", referencedColumnName = "id")
    private Estatistica estatistica;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    private Pokemon pokemon;

    @ManyToMany
    @JoinTable(
            name = "especie_habilidades",
            joinColumns = @JoinColumn(name = "especie_id"),
            inverseJoinColumns = @JoinColumn(name = "habilidades_id")
    )
    private List<Habilidades> habilidades;
}
