package ufg.inf.cs.ApiRestPokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.classes.ItemDTO;
import ufg.inf.cs.ApiRestPokedex.service.TreinadorService;

import java.util.List;

@Controller
@RequestMapping
public class TreinadorController {

    @Autowired
    public TreinadorService treinadorService;

    @GetMapping("/{treinadorId}/itens")
    public ResponseEntity<List<ItemDTO>> getItensDoTreinador(@PathVariable Long treinadorId) {
        List<ItemDTO> itens = treinadorService.getItensDoTreinador(treinadorId);
        return ResponseEntity.ok(itens);
    }

    @DeleteMapping("/{treinadorId}/itens/{itemId}")
    public ResponseEntity<String> consumirItemDoTreinador(@PathVariable Long treinadorId, @PathVariable Long itemId) {
        treinadorService.consumirItem(treinadorId, itemId);
        return ResponseEntity.ok ("Item usado com sucesso");
    }

    @PatchMapping("/{treinadorId}")
    public ResponseEntity<String> subirDeNivel(@PathVariable Long treinadorId) {
        treinadorService.subirNivel(treinadorId);
        return ResponseEntity.ok("Parabens! Você subiu de nivel");
    }
}
