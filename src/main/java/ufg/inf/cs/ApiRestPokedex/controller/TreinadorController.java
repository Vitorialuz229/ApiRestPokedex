package ufg.inf.cs.ApiRestPokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ufg.inf.cs.ApiRestPokedex.classes.ItemDTO;
import ufg.inf.cs.ApiRestPokedex.service.TreinadorService;

import java.util.List;

@Controller
public class TreinadorController {

    @Autowired
    public TreinadorService treinadorService;

    @GetMapping("/{treinadorId}/itens")
    public ResponseEntity<List<ItemDTO>> getItensDoTreinador(@PathVariable Long treinadorId) {
        List<ItemDTO> itens = treinadorService.getItensDoTreinador(treinadorId);
        return ResponseEntity.ok(itens);
    }
}
