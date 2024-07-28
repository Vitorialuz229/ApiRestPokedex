package ufg.inf.cs.ApiRestPokedex.controller.treinador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.item.ItemDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.service.treinador.TreinadorService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/treinador")
public class TreinadorController {

    @Autowired
    private TreinadorService treinadorService;

    /**
     * Listar todos os itens pertencentes a um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @return Lista de itens do treinador.
     */
    @GetMapping("/{treinadorId}/itens")
    public ResponseEntity<List<ItemDTO>> getItensDoTreinador(@PathVariable Long treinadorId) {
        List<ItemDTO> itens = treinadorService.getItensDoTreinador(treinadorId);
        return ResponseEntity.ok(itens);
    }

    /**
     * Consumir um item e retirá-lo da lista do treinador.
     *
     * @param treinadorId ID do treinador.
     * @param itemId ID do item a ser consumido.
     * @return Mensagem de sucesso.
     */
    @DeleteMapping("/{treinadorId}/itens/{itemId}")
    public ResponseEntity<String> consumirItemDoTreinador(@PathVariable Long treinadorId, @PathVariable Long itemId) {
        treinadorService.consumirItem(treinadorId, itemId);
        return ResponseEntity.ok("Item usado com sucesso");
    }

    /**
     * Atualiza o nível de um treinador para +1.
     *
     * @param treinadorId ID do treinador.
     * @return Mensagem de sucesso.
     */
    @PatchMapping("/{treinadorId}")
    public ResponseEntity<String> subirDeNivel(@PathVariable Long treinadorId) {
        treinadorService.subirNivel(treinadorId);
        return ResponseEntity.ok("Parabéns! Você subiu de nível");
    }

    /**
     * Listar todos os Pokémon pertencentes a um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @return Lista de Pokémons do treinador.
     */
    @GetMapping("/{treinadorId}/pokemons")
    public ResponseEntity<List<PokemonDTO>> getPokemonsDoTreinador(@PathVariable Long treinadorId) {
        List<PokemonDTO> pokemons = treinadorService.getPokemonsDoTreinador(treinadorId);
        return ResponseEntity.ok(pokemons);
    }

    /**
     * Endpoint para o treinador escolher um Pokémon inicial.
     *
     * @param treinadorId ID do treinador.
     * @param pokemonNome Nome do Pokémon a ser escolhido.
     */
    @PostMapping("/{treinadorId}/escolher-pokemon")
    public ResponseEntity<Void> escolherPokemonInicial(@PathVariable Long treinadorId, @RequestParam String pokemonNome) {
        try {
            treinadorService.escolherPokemonInicial(treinadorId, pokemonNome);
            return ResponseEntity.ok().build();
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

