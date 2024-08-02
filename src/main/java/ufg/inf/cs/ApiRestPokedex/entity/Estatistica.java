package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Estatistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int saude;

    @Column
    private int ataque;

    @Column
    private int defesa;

    @Column
    private int velocidade;

    @Column
    private int ataqueEspecial;

    @Column
    private int DefesaEspecial;
}
