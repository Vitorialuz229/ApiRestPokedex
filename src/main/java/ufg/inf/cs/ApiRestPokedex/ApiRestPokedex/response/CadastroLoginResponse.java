package ufg.inf.cs.ApiRestPokedex.response;

import lombok.Builder;
import lombok.Data;
import ufg.inf.cs.ApiRestPokedex.entity.Login;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;

@Data
@Builder
public class CadastroLoginResponse {
    private String mensagem;
    private Login login;
    private Treinador treinador;
}
