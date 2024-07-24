package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Seguimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "seguidor_id", nullable = false)
    private Treinador seguidor;

    @ManyToOne
    @JoinColumn(name = "seguido_id", nullable = false)
    private Treinador seguido;

    private Boolean favorito;

}
