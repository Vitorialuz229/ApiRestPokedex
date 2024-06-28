package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Treinador {

    @Id
    private int id; // Este será o mesmo ID de Login

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column
    private int nivel;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId // Usa o ID de login para o atributo id
    @JoinColumn(name = "id") // Indica pra qual campo vai a chave estrangeira
    private Login login;



}
