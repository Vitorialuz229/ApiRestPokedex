package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "pokedex_id", referencedColumnName = "id")
    private Pokedex pokedex;

    @OneToMany(mappedBy = "treinador", cascade = CascadeType.ALL)
    private Set<Amizade> amigos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;

}
