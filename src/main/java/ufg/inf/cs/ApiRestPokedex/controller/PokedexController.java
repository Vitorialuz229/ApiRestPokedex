package ufg.inf.cs.ApiRestPokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import ufg.inf.cs.ApiRestPokedex.service.PokedexService;

import java.util.List;

@RestController
@RequestMapping("/pokedex")
public class PokedexController {

    @Autowired
    private PokedexService pokedexService;

    @GetMapping("/")
    public ResponseEntity<List<Pokedex>> listarEspecies() {
        return ResponseEntity.status(HttpStatus.OK).body(pokedexService.listarTodasPokedex());
    }

    @GetMapping("/buscar")
    public Pokedex listarPokedex(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome) {
        return pokedexService.listarPokedex(id, nome);
    }
}
