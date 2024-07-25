package ufg.inf.cs.ApiRestPokedex.DTO.treinador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreinamentoDTO {

    private int experienciaAdicional;
    private int aumentoAmizade;

    public TreinamentoDTO (int experienciaAdicional, int aumentoAmizade) {
        this.experienciaAdicional = experienciaAdicional;
        this.aumentoAmizade = aumentoAmizade;
    }
}
