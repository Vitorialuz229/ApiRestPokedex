package ufg.inf.cs.ApiRestPokedex.DTO.treinador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreinadorSeguidoDTO {

    private Long id;
    private String nome;
    private Boolean favorito;

    public TreinadorSeguidoDTO (Long id, String nome, Boolean favorito) {
        this.id = id;
        this.nome = nome;
        this.favorito = favorito;
    }
}
