package ufg.inf.cs.ApiRestPokedex.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.DTO.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.entity.*;
import ufg.inf.cs.ApiRestPokedex.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private TreinadorPokemonRepository treinadorPokemonRepository;

    @Autowired
    private PokedexRepository pokedexRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Transactional
    public PokemonDTO salvarPokemon(Long treinadorId, String nomePokemon) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        Pokedex pokedex = treinador.getPokedex();
        if (pokedex == null) {
            pokedex = new Pokedex();
            pokedex.setNome("Pokédex do Treinador " + treinadorId);
            pokedex.setTreinador(treinador);
            pokedex = pokedexRepository.save(pokedex);
            treinador.setPokedex(pokedex);
            treinadorRepository.save(treinador);
        }

        Especie especieSelecionada = especieRepository
                .findByNameIgnoreCase(nomePokemon)
                .orElseThrow(() -> new RuntimeException("Espécie " + nomePokemon + " não encontrada"));

        Pokemon pokemon = new Pokemon();
        pokemon.setApelido(especieSelecionada.getName());
        pokemon.setNivel(1);
        pokemon.setEspecie(especieSelecionada);
        pokemon.setPokedex(pokedex);

        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        TreinadorPokemon treinadorPokemon = new TreinadorPokemon();
        treinadorPokemon.setTreinador(treinador);
        treinadorPokemon.setPokemon(savedPokemon);
        treinadorPokemon.setNivelAmizade(0);
        treinadorPokemonRepository.save(treinadorPokemon);

        return new PokemonDTO(
                savedPokemon.getId(),
                savedPokemon.getApelido(),
                savedPokemon.getNivel(),
                savedPokemon.getEspecie(),
                savedPokemon.getEspecie().getEstatistica(),
                treinadorPokemon.getNivelAmizade()
        );
    }

    @Transactional
    public List<PokemonDTO> listarPokemonsPorTreinador(Long treinadorId) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        Pokedex pokedex = treinador.getPokedex();
        if (pokedex == null) {
            throw new RuntimeException("Pokédex não encontrada para o treinador " + treinadorId);
        }

        List<Pokemon> pokemons = pokemonRepository.findByPokedex(pokedex);
        return pokemons.stream().map(pokemon -> {
            TreinadorPokemon treinadorPokemon = treinadorPokemonRepository.findByTreinadorIdAndPokemonId(treinadorId, pokemon.getId())
                    .orElseThrow(() -> new RuntimeException("Relação Treinador-Pokémon não encontrada"));
            return new PokemonDTO(
                    pokemon.getId(),
                    pokemon.getApelido(),
                    pokemon.getNivel(),
                    pokemon.getEspecie(),
                    pokemon.getEspecie().getEstatistica(),
                    treinadorPokemon.getNivelAmizade()
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public List<PokemonDTO> listarPokemonsPorTreinadorEPorNome(Long treinadorId, String apelidoPokemon) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        Pokedex pokedex = treinador.getPokedex();
        if (pokedex == null) {
            throw new RuntimeException("Pokédex não encontrada para o treinador " + treinadorId);
        }

        List<Pokemon> pokemons = pokemonRepository.findByPokedexAndApelidoContainingIgnoreCase(pokedex, apelidoPokemon);
        return pokemons.stream().map(pokemon -> {
            TreinadorPokemon treinadorPokemon = treinadorPokemonRepository.findByTreinadorIdAndPokemonId(treinadorId, pokemon.getId())
                    .orElseThrow(() -> new RuntimeException("Relação Treinador-Pokémon não encontrada"));
            return new PokemonDTO(
                    pokemon.getId(),
                    pokemon.getApelido(),
                    pokemon.getNivel(),
                    pokemon.getEspecie(),
                    pokemon.getEspecie().getEstatistica(),
                    treinadorPokemon.getNivelAmizade()
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public PokemonDTO atualizarApelidoPokemon(Long treinadorId, Long pokemonId, String novoApelido) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

        Pokedex pokedex = treinador.getPokedex();
        if (pokedex == null || !pokedex.equals(pokemon.getPokedex())) {
            throw new RuntimeException("Pokémon não pertence ao treinador " + treinadorId);
        }

        pokemon.setApelido(novoApelido);
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        TreinadorPokemon treinadorPokemon = treinadorPokemonRepository.findByTreinadorIdAndPokemonId(treinadorId, pokemonId)
                .orElseThrow(() -> new RuntimeException("Relação Treinador-Pokémon não encontrada"));

        return new PokemonDTO(
                updatedPokemon.getId(),
                updatedPokemon.getApelido(),
                updatedPokemon.getNivel(),
                updatedPokemon.getEspecie(),
                updatedPokemon.getEspecie().getEstatistica(),
                treinadorPokemon.getNivelAmizade()
        );
    }

    @Transactional
    public PokemonDTO treinarPokemon(Long treinadorId, Long pokemonId) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

        Pokedex pokedex = treinador.getPokedex();
        if (pokedex == null) {
            throw new RuntimeException("Pokédex não encontrada para o treinador " + treinadorId);
        }

        if (!pokedex.getPokemons().contains(pokemon)) {
            throw new RuntimeException("Treinador não possui o Pokémon especificado");
        }

        int novoNivel = pokemon.getNivel() + 1;
        pokemon.setNivel(novoNivel);

        for (MovimentosPokemon movimentosPokemon : pokemon.getMovimentosPokemon()) {
            int nivelMovimento = movimentosPokemon.getLevel();
            movimentosPokemon.setLevel(nivelMovimento + 1);
        }

        atualizarNivelAmizade(treinadorId, pokemonId, 1);

        Pokemon pokemonAtualizado = pokemonRepository.save(pokemon);

        TreinadorPokemon treinadorPokemon = treinadorPokemonRepository.findByTreinadorIdAndPokemonId(treinadorId, pokemonId)
                .orElseThrow(() -> new RuntimeException("Relação Treinador-Pokémon não encontrada"));

        return new PokemonDTO(
                pokemonAtualizado.getId(),
                pokemonAtualizado.getApelido(),
                pokemonAtualizado.getNivel(),
                pokemonAtualizado.getEspecie(),
                pokemonAtualizado.getEspecie().getEstatistica(),
                treinadorPokemon.getNivelAmizade()
        );
    }

    @Transactional
    public PokemonDTO deletePokemon(Long treinadorId, Long pokemonId) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

        Pokedex pokedex = treinador.getPokedex();
        if (pokedex == null) {
            throw new RuntimeException("Pokédex não encontrada para o treinador " + treinadorId);
        }

        if (!pokedex.getPokemons().contains(pokemon)) {
            throw new RuntimeException("Treinador não possui o Pokémon especificado");
        }

        pokedex.getPokemons().remove(pokemon);
        treinadorRepository.save(treinador);

        TreinadorPokemon treinadorPokemon = treinadorPokemonRepository.findByTreinadorIdAndPokemonId(treinadorId, pokemonId)
                .orElseThrow(() -> new RuntimeException("Relação Treinador-Pokémon não encontrada"));

        treinadorPokemonRepository.delete(treinadorPokemon);

        pokemonRepository.delete(pokemon);

        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getApelido(),
                pokemon.getNivel(),
                pokemon.getEspecie(),
                pokemon.getEspecie().getEstatistica(),
                treinadorPokemon.getNivelAmizade()
        );
    }

    @Transactional
    public void atualizarNivelAmizade(Long treinadorId, Long pokemonId, int aumento) {
        TreinadorPokemon treinadorPokemon = treinadorPokemonRepository.findByTreinadorIdAndPokemonId(treinadorId, pokemonId)
                .orElseThrow(() -> new RuntimeException("Relação Treinador-Pokémon não encontrada"));

        int novoNivelAmizade = calcularNovoNivelAmizade(treinadorPokemon.getNivelAmizade(), aumento);
        treinadorPokemon.setNivelAmizade(novoNivelAmizade);
        treinadorPokemonRepository.save(treinadorPokemon);
    }

    private int calcularNovoNivelAmizade(int nivelAtual, int aumento) {
        return nivelAtual + aumento;
    }
}
