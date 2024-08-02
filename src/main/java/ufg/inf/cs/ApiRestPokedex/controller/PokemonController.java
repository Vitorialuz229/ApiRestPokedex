package ufg.inf.cs.ApiRestPokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.service.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @PostMapping("/save/{treinadorId}/pokemon/{nomePokemon}")
    public ResponseEntity<PokemonDTO> salvarPokemon(
            @PathVariable Long treinadorId,
            @PathVariable String nomePokemon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.salvarPokemon(treinadorId, nomePokemon));
    }

    @GetMapping("/listar/{treinadorId}")
    public ResponseEntity<List<PokemonDTO>> listarPokemonsPorTreinador(@PathVariable Long treinadorId) {
        List<PokemonDTO> pokemons = pokemonService.listarPokemonsPorTreinador(treinadorId);
        return ResponseEntity.status(HttpStatus.OK).body(pokemons);
    }

    @GetMapping("/listar/{treinadorId}/nome/{apelidoPokemon}")
    public ResponseEntity<List<PokemonDTO>> listarPokemonsPorTreinadorEPorNome(@PathVariable Long treinadorId, @PathVariable String apelidoPokemon) {
        List<PokemonDTO> pokemons = pokemonService.listarPokemonsPorTreinadorEPorNome(treinadorId, apelidoPokemon);
        return ResponseEntity.status(HttpStatus.OK).body(pokemons);
    }

    @PutMapping("/atualizar-apelido/{treinadorId}/{pokemonId}")
    public ResponseEntity<PokemonDTO> atualizarApelidoPokemon(
            @PathVariable Long treinadorId,
            @PathVariable Long pokemonId,
            @RequestBody String novoApelido) {
        novoApelido = novoApelido.trim().replaceAll("^\"|\"$", "");
        PokemonDTO updatedPokemon = pokemonService.atualizarApelidoPokemon(treinadorId, pokemonId, novoApelido);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPokemon);
    }

    @PutMapping("/treinar/{treinadorId}/{pokemonId}")
    public ResponseEntity<PokemonDTO> treinarPokemon(
            @PathVariable Long treinadorId,
            @PathVariable Long pokemonId) {
        PokemonDTO updatedPokemon = pokemonService.treinarPokemon(treinadorId, pokemonId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPokemon);
    }

    @DeleteMapping("/{treinadorId}/pokemons/{pokemonId}")
    public ResponseEntity<PokemonDTO> deletePokemon(
            @PathVariable Long treinadorId,
            @PathVariable Long pokemonId) {
        PokemonDTO deletedPokemon = pokemonService.deletePokemon(treinadorId, pokemonId);
        return ResponseEntity.ok(deletedPokemon);
    }
}
