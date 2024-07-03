package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;

@Entity
public class Amizade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "treinador_id", nullable = false)
    private Treinador treinador;

    @ManyToOne
    @JoinColumn(name = "amigo_id", nullable = false)
    private Treinador amigo;

    private Boolean favorito;

}
