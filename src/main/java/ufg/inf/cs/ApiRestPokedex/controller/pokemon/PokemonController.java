package ufg.inf.cs.ApiRestPokedex.controller.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinadorDTO;
import ufg.inf.cs.ApiRestPokedex.adapter.PokemonAdapter;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;
import ufg.inf.cs.ApiRestPokedex.service.pokemon.PokemonService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private RestTemplate restTemplate;

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
     * @param treinadorDTO Dados do treinamento.
     * @return Detalhes do Pokémon após treinamento.
     */
    @PutMapping("/{pokemonId}/treinar")
    public ResponseEntity<PokemonDTO> treinarPokemon(@PathVariable Long pokemonId, @RequestBody TreinadorDTO treinadorDTO) {
        PokemonDTO pokemon = pokemonService.treinarPokemon(pokemonId, treinadorDTO);
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

    /**
     * Buscar Pokémon primários.
     *
     * @return Lista de Pokémon primários.
     * @throws IOException Se ocorrer um erro ao buscar dados externos.
     */
    @GetMapping("/primarios")
    public ResponseEntity<List<Pokemon>> getPokemonPrimarios() throws IOException {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=3";
        PokemonAdapter response = restTemplate.getForObject(url, PokemonAdapter.class);

        if (response == null || response.getResults() == null) {
            return ResponseEntity.notFound().build();
        }

        List<Pokemon> pokemons = response.getResults().stream()
                .filter(pokemon -> {
                    String apelido = pokemon.getApelido();
                    return apelido != null && (apelido.equals("bulbasaur") ||
                            apelido.equals("charmander") ||
                            apelido.equals("squirtle"));
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(pokemons);
    }
}