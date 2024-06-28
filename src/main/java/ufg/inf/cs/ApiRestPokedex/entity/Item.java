package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id
    private int id;
    private String nome;
    private String descricao;
}
