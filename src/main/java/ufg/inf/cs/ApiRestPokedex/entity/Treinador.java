package ufg.inf.cs.ApiRestPokedex.entity;

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
    @MapsId // Usa o ID de login para o atributo id
    @JoinColumn(name = "login_id", referencedColumnName = "id") // Indica pra qual campo vai a chave estrangeira
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pokedex_id", referencedColumnName = "id")
    private Pokedex pokedex;

    @OneToMany(mappedBy = "treinador", cascade = CascadeType.ALL)
    private Set<Amizade> amigos = new HashSet<>();

    @OneToMany(mappedBy = "treinador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

}
