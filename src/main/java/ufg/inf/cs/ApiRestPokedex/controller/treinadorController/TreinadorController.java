package ufg.inf.cs.ApiRestPokedex.controller.treinadorController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.item.ItemDTO;
import ufg.inf.cs.ApiRestPokedex.service.treinador.TreinadorService;

import java.util.List;

@Controller
@RequestMapping
public class TreinadorController {

    @Autowired
    public TreinadorService treinadorService;

    // Listar todos os itens pertencentes a um treinador
    @GetMapping("/{treinadorId}/itens")
    public ResponseEntity<List<ItemDTO>> getItensDoTreinador(@PathVariable Long treinadorId) {
        List<ItemDTO> itens = treinadorService.getItensDoTreinador(treinadorId);
        return ResponseEntity.ok(itens);
    }

    // Consumir um item e retira-lo da lista do treinador
    @DeleteMapping("/{treinadorId}/itens/{itemId}")
    public ResponseEntity<String> consumirItemDoTreinador(@PathVariable Long treinadorId, @PathVariable Long itemId) {
        treinadorService.consumirItem(treinadorId, itemId);
        return ResponseEntity.ok ("Item usado com sucesso");
    }

    // Atualiza o nivel de um treinador para +1
    @PatchMapping("/{treinadorId}")
    public ResponseEntity<String> subirDeNivel(@PathVariable Long treinadorId) {
        treinadorService.subirNivel(treinadorId);
        return ResponseEntity.ok("Parabens! Você subiu de nivel");
    }
}
