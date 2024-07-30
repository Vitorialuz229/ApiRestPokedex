package ufg.inf.cs.ApiRestPokedex.DTO.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinadorDTO;

@Data
@AllArgsConstructor
public class LoginDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("email")
    private String email;

    @JsonProperty("treinador")
    private TreinadorDTO treinadorDTO;
}
