package ufg.inf.cs.ApiRestPokedex.controller.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinamentoDTO;
import ufg.inf.cs.ApiRestPokedex.service.pokemon.PokemonService;

@Controller
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    /**
     * Buscar Pokémon por ID.
     *
     * @param pokemonId ID do Pokémon.
     * @return Detalhes do Pokémon.
     */
    @GetMapping("/{pokemonId}")
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable Long pokemonId) {
        PokemonDTO pokemon = pokemonService.getPokemonById(pokemonId);
        return ResponseEntity.ok(pokemon);
    }

    /**
     * Buscar Pokémon por nome.
     *
     * @param nome Nome do Pokémon.
     * @return Detalhes do Pokémon.
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<PokemonDTO> getPokemonByName(@PathVariable String nome) {
        PokemonDTO pokemon = pokemonService.getPokemonByName(nome);
        return ResponseEntity.ok(pokemon);
    }

    /**
     * Deletar um Pokémon pelo ID.
     *
     * @param pokemonId ID do Pokémon.
     * @return Resposta sem conteúdo.
     */
    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long pokemonId) {
        pokemonService.deletePokemon(pokemonId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Treinar um Pokémon.
     *
     * @param pokemonId ID do Pokémon.
     * @param treinamentoDTO Dados do treinamento.
     * @return Detalhes do Pokémon após treinamento.
     */
    @PutMapping("/{pokemonId}/treinar")
    public ResponseEntity<PokemonDTO> treinarPokemon(@PathVariable Long pokemonId, @RequestBody TreinamentoDTO treinamentoDTO) {
        PokemonDTO pokemon = pokemonService.treinarPokemon(pokemonId, treinamentoDTO);
        return ResponseEntity.ok(pokemon);
    }

    /**
     * Evoluir um Pokémon.
     *
     * @param pokemonId ID do Pokémon.
     * @return Detalhes do Pokémon após evolução.
     */
    @PutMapping("/{pokemonId}/evoluir")
    public ResponseEntity<PokemonDTO> evoluirPokemon(@PathVariable Long pokemonId) {
        PokemonDTO pokemon = pokemonService.evoluirPokemon(pokemonId);
        return ResponseEntity.ok(pokemon);
    }
}
