package ufg.inf.cs.ApiRestPokedex.controller.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinadorDTO;
import ufg.inf.cs.ApiRestPokedex.exception.ResourceNotFoundException;
import ufg.inf.cs.ApiRestPokedex.service.pokemon.PokemonService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private RestTemplate restTemplate;

     /**
     * Buscar Pokémon por ID dentro da lista de um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @param pokemonId ID do Pokémon.
     * @return Detalhes do Pokémon.
     */
    @GetMapping("/{treinadorId}/pokemons/{pokemonId}")
    public ResponseEntity<PokemonDTO> getPokemonById(
            @PathVariable Long treinadorId,
            @PathVariable Long pokemonId) {
        PokemonDTO pokemon = pokemonService.getPokemonByIdAndTreinador(treinadorId, pokemonId);
        return ResponseEntity.ok(pokemon);
    }

    /**
     * Buscar Pokémon por nome dentro da lista de um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @param nome Nome do Pokémon.
     * @return Detalhes do Pokémon.
     */
    @GetMapping("/{treinadorId}/pokemons/nome/{nome}")
    public ResponseEntity<PokemonDTO> getPokemonByName(
            @PathVariable Long treinadorId,
            @PathVariable String nome) {
        PokemonDTO pokemon = pokemonService.getPokemonByNameAndTreinador(treinadorId, nome);
        return ResponseEntity.ok(pokemon);
    }

    /**
     * Treinar um Pokémon dentro da lista de um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @param pokemonId ID do Pokémon.
     * @param treinadorDTO Dados do treinamento.
     * @return Detalhes do Pokémon após treinamento.
     */
    @PutMapping("/{treinadorId}/pokemons/{pokemonId}/treinar")
    public ResponseEntity<PokemonDTO> treinarPokemon(
            @PathVariable Long treinadorId,
            @PathVariable Long pokemonId,
            @RequestBody TreinadorDTO treinadorDTO) {
        PokemonDTO pokemon = pokemonService.treinarPokemon(treinadorId, pokemonId, treinadorDTO);
        return ResponseEntity.ok(pokemon);
    }

    /**
     * Endpoint para remover um Pokémon da lista de um treinador específico.
     *
     * @param treinadorId ID do treinador do qual o Pokémon será removido.
     * @param pokemonId ID do Pokémon a ser removido.
     * @return Resposta HTTP com mensagem de sucesso ou erro.
     */
    @DeleteMapping("/{treinadorId}/pokemons/{pokemonId}")
    public ResponseEntity<String> removerPokemon(
            @PathVariable("treinadorId") Long treinadorId,
            @PathVariable("pokemonId") int pokemonId) {
        try {
            // Remove o Pokémon da lista do treinador
            pokemonService.removerPokemonDoTreinador(treinadorId, pokemonId);
            return ResponseEntity.ok("Pokémon removido com sucesso.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o recurso não for encontrado
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao remover Pokémon: " + e.getMessage());
        }
    }

    /**
     * Listar todos os Pokémon pertencentes a um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @return Lista de Pokémons do treinador.
     */
    @GetMapping("/{treinadorId}/pokemons")
    public ResponseEntity<List<PokemonDTO>> getPokemonsDoTreinador(@PathVariable Long treinadorId) {
        List<PokemonDTO> pokemons = pokemonService.getPokemonsDoTreinador(treinadorId);
        return ResponseEntity.ok(pokemons);
    }

    /**
     * Endpoint para evoluir um Pokémon da lista de um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @param pokemonId ID do Pokémon a ser evoluído.
     * @return Resposta HTTP com mensagem de sucesso ou erro.
     */
    @PutMapping("/{treinadorId}/pokemons/{pokemonId}/evoluir")
    public ResponseEntity<String> evoluirPokemon(
            @PathVariable("treinadorId") Long treinadorId,
            @PathVariable("pokemonId") int pokemonId) {
        try {
            pokemonService.evoluirPokemon(treinadorId, pokemonId);
            return ResponseEntity.ok("Pokémon evoluído com sucesso.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao evoluir Pokémon: " + e.getMessage());
        }
    }

    /**
     * Endpoint para salvar os Pokémon primários (Squirtle, Bulbasaur, Charmander) para um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @return Lista de Pokémon salvos.
     */
    @PostMapping("/{treinadorId}/salvar-pokemons")
    public ResponseEntity<List<PokemonDTO>> salvarPokemonPrimarios(@PathVariable Long treinadorId) {
        try {
            List<PokemonDTO> savedPokemons = (List<PokemonDTO>) pokemonService.salvarPokemonPrimario(treinadorId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemons);
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Endpoint para salvar um Pokémon da API externa para um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @param pokemonNome Nome do Pokémon a ser salvo.
     * @return Detalhes do Pokémon salvo.
     */
    @PostMapping("/{treinadorId}/salvar-pokemon")
    public ResponseEntity<PokemonDTO> salvarPokemonPorNomeParaTreinador(@PathVariable Long treinadorId, @RequestParam String pokemonNome) {
        try {
            PokemonDTO savedPokemon = pokemonService.salvarPokemonPorNomeParaTreinador(treinadorId, pokemonNome);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemon);
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Endpoint para aumentar o nível de amizade de um Pokémon com um treinador específico.
     *
     * @param pokemonId ID do Pokémon.
     * @param treinadorId ID do treinador.
     * @param aumento Quantidade de aumento na amizade.
     * @return Resposta HTTP com mensagem de sucesso ou erro.
     */
    @PutMapping("/{pokemonId}/amizade")
    public ResponseEntity<String> aumentarNivelAmizade(
            @PathVariable("pokemonId") int pokemonId,
            @RequestParam("treinadorId") Long treinadorId,
            @RequestParam("aumento") int aumento) {
        try {
            pokemonService.atualizarNivelAmizade(pokemonId, treinadorId, aumento);
            return ResponseEntity.ok("Nível de amizade atualizado com sucesso.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar nível de amizade: " + e.getMessage());
        }
    }
}