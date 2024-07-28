package ufg.inf.cs.ApiRestPokedex.DTO.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    private int id;
    private String nome;
    private String descricao;

    public ItemDTO (int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
}
