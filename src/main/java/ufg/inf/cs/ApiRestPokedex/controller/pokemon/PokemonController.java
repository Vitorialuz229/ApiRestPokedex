package ufg.inf.cs.ApiRestPokedex.controller.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinamentoDTO;
import ufg.inf.cs.ApiRestPokedex.service.pokemon.PokemonService;
import ufg.inf.cs.ApiRestPokedex.service.treinador.TreinadorService;

import java.util.List;

@Controller
@RequestMapping
public class PokemonController {

    @Autowired
    public TreinadorService treinadorService;

    @Autowired
    private PokemonService pokemonService;

    //Busca pokemon por id
    @GetMapping("/pokemons/{pokemonId}")
    public ResponseEntity<PokemonDTO> getPokemonById (@PathVariable Long pokemonId) {
        PokemonDTO pokemon = pokemonService.getPokemonById (pokemonId);
        return ResponseEntity.ok (pokemon);
    }

    //Busca pokemon por nome
    @GetMapping("/pokemons/nome/{nome}")
    public ResponseEntity<PokemonDTO> getPokemonByName (@PathVariable String nome) {
        PokemonDTO pokemon = pokemonService.getPokemonByName (nome);
        return ResponseEntity.ok (pokemon);
    }

    // Listar todos os Pokémons pertencentes a um treinador
    @GetMapping("/{treinadorId}/pokemons")
    public ResponseEntity<List<PokemonDTO>> getPokemonsDoTreinador (@PathVariable Long treinadorId) {
        List<PokemonDTO> pokemons = treinadorService.getPokemonsDoTreinador (treinadorId);
        return ResponseEntity.ok (pokemons);
    }

    //Deletar pokemon
    @DeleteMapping("/pokemons/{pokemonId}")
    public ResponseEntity<Void> deletePokemon (@PathVariable Long pokemonId) {
        pokemonService.deletePokemon (pokemonId);
        return ResponseEntity.noContent ().build ();
    }

    //Treinamento do pokemon
    @PutMapping("/pokemons/{pokemonId}/treinar")
    public ResponseEntity<PokemonDTO> treinarPokemon (@PathVariable Long pokemonId, @RequestBody TreinamentoDTO treinamentoDTO) {
        PokemonDTO pokemon = pokemonService.treinarPokemon (pokemonId, treinamentoDTO);
        return ResponseEntity.ok (pokemon);
    }

    //Evoluir pokemon
    @PutMapping("/pokemons/{pokemonId}/evoluir")
    public ResponseEntity<PokemonDTO> evoluirPokemon (@PathVariable Long pokemonId) {
        PokemonDTO pokemon = pokemonService.evoluirPokemon (pokemonId);
        return ResponseEntity.ok (pokemon);
    }
}
