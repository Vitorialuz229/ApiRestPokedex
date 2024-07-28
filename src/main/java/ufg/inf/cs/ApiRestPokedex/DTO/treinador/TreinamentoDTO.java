package ufg.inf.cs.ApiRestPokedex.DTO.treinador;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TreinamentoDTO {

    private int experienciaAdicional;
    private int aumentoAmizade;

    public TreinamentoDTO(int experienciaAdicional, int aumentoAmizade) {
        this.experienciaAdicional = experienciaAdicional;
        this.aumentoAmizade = aumentoAmizade;
    }
}
