package ufg.inf.cs.ApiRestPokedex.controller.treinador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.item.ItemDTO;
import ufg.inf.cs.ApiRestPokedex.service.treinador.TreinadorService;

import java.util.List;

@RestController
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
     * @param itemId      ID do item a ser consumido.
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
}