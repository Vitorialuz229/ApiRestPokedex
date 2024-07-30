package ufg.inf.cs.ApiRestPokedex.DTO.especie;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecieDTO {
    private Long id;
    private String name;
    private String types;
    private String variation;
    private String url;

    /**
     * Converte a entidade Especie para o DTO EspecieDTO.
     *
     * @param especie A entidade Especie.
     * @return O DTO EspecieDTO correspondente.
     */
    public static EspecieDTO converterParaEspecieDTO(Especie especie) {
        EspecieDTO especieDTO = new EspecieDTO();
        especieDTO.setId(especie.getId());
        especieDTO.setName(especie.getName());
        especieDTO.setTypes(especie.getTypes());
        especieDTO.setVariation(especie.getVariation());
        especieDTO.setUrl(especie.getUrl());
        return especieDTO;
    }
}
