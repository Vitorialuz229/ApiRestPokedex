package ufg.inf.cs.ApiRestPokedex.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String mensagem;
    private Treinador treinador;
}
