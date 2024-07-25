package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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
