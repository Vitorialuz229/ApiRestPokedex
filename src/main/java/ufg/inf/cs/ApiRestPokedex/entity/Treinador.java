package ufg.inf.cs.ApiRestPokedex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
public class Treinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Este será o mesmo ID de Login

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column
    private int nivel;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @MapsId // Usa o ID de login para o atributo id
    @JoinColumn(name = "login_id", referencedColumnName = "id") // Indica pra qual campo vai a chave estrangeira
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pokedex_id", referencedColumnName = "id")
    private Pokedex pokedex;

    @OneToMany(mappedBy = "seguidor", cascade = CascadeType.ALL)
    private Set<Seguimento> seguidos = new HashSet<> ();

    @ManyToMany
    @JoinTable(
            name = "treinador_itens",
            joinColumns = @JoinColumn(name = "treinador_id"),
            inverseJoinColumns = @JoinColumn(name = "itens_id")
    )
    private Set<Item> itens = new HashSet<> ();

    @OneToMany(mappedBy = "treinador", cascade = CascadeType.ALL)
    private Set<TreinadorPokemon> treinadorPokemons = new HashSet<>();
}
