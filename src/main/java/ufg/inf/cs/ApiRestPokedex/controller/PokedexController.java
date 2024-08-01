package ufg.inf.cs.ApiRestPokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;
import ufg.inf.cs.ApiRestPokedex.service.PokedexService;

import java.util.List;

@RestController
@RequestMapping("/pokedex")
public class PokedexController {

    @Autowired
    private PokedexService pokedexService;

    @PostMapping("/save/{treinadorId}/pokemon-primario/{nomePokemonPrimario}")
    public ResponseEntity<PokemonDTO> salvarPokemonPrimario(
            @PathVariable Long treinadorId,
            @PathVariable String nomePokemonPrimario) {
        PokemonDTO pokemonDTO = pokedexService.salvarPokemonPrimario(treinadorId, nomePokemonPrimario);
        return ResponseEntity.ok(pokemonDTO);
    }

    @GetMapping("/")
    public List<Pokedex> listarEspecies() {
        return pokedexService.listarTodasPokedex();
    }


    @GetMapping("/buscar")
    public Pokedex listarPokedex(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome) {
        return pokedexService.listarPokedex(id, nome);
    }
}
