package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String apelido;

    @Column
    private int nivel;

    private String nivelAmizade;

}
