package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int numero;
    private String descricao;
    private char genero;

    @ManyToOne
    private Especie especieBase;
}
