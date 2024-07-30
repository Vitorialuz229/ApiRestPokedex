package ufg.inf.cs.ApiRestPokedex.DTO.treinador;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TreinadorDTO {

    @JsonProperty("login_id")
    private long loginId;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("data_nascimento")
    private Date dataNascimento;

    @JsonProperty("nivel")
    private int nivel;

    @JsonProperty("pokedex_id")
    private int pokedexId;
}
