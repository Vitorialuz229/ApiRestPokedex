package ufg.inf.cs.ApiRestPokedex.DTO.item;

public class ItemDTO {

    private int id;
    private String nome;
    private String descricao;

    public ItemDTO(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }
}
