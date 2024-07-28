package ufg.inf.cs.ApiRestPokedex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ufg.inf.cs.ApiRestPokedex.controller.treinador.TreinadorController;
import ufg.inf.cs.ApiRestPokedex.service.treinador.TreinadorService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TreinadorController.class)
public class TreinadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreinadorService treinadorService;

    @Test
    public void testEscolherPokemonInicial_Success() throws Exception {
        Long treinadorId = 1L;
        String pokemonNome = "charmander";

        // Simule o sucesso no serviço
        doNothing().when(treinadorService).escolherPokemonInicial(treinadorId, pokemonNome);

        mockMvc.perform(post("/treinador/{treinadorId}/escolher-pokemon", treinadorId)
                        .param("pokemonNome", pokemonNome))
                .andExpect(status().isOk());
    }

    @Test
    public void testEscolherPokemonInicial_Failure() throws Exception {
        Long treinadorId = 1L;
        String pokemonNome = "invalidPokemon";

        // Simule uma exceção no serviço
        doThrow(new RuntimeException("Pokémon não encontrado com o nome: " + pokemonNome))
                .when(treinadorService).escolherPokemonInicial(treinadorId, pokemonNome);

        mockMvc.perform(post("/treinador/{treinadorId}/escolher-pokemon", treinadorId)
                        .param("pokemonNome", pokemonNome))
                .andExpect(status().isBadRequest());
    }
}
