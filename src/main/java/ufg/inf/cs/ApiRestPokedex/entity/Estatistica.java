package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Estatistica {

    @Id
    private int id;

    private int saude;
    private int ataque;
    private int defesa;
    private int velocidade;
    private int ataqueEspecial;
    private int DefesaEspecial;

}