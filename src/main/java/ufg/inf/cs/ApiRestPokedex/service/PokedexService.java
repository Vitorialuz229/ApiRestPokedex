package ufg.inf.cs.ApiRestPokedex.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.DTO.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.entity.*;
import ufg.inf.cs.ApiRestPokedex.repository.*;

import java.util.List;

@Service
public class PokedexService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private PokedexRepository pokedexRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Transactional
    public PokemonDTO salvarPokemonPrimario(Long treinadorId, String nomePokemonPrimario) {
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
                .findByNameIgnoreCase(nomePokemonPrimario)
                .orElseThrow(() -> new RuntimeException("Espécie " + nomePokemonPrimario + " não encontrada"));

        Pokemon pokemon = new Pokemon();
        pokemon.setApelido(especieSelecionada.getName());
        pokemon.setNivel(1);
        pokemon.setNivelAmizade("0");
        pokemon.setEspecie(especieSelecionada);
        pokemon.setPokedex(pokedex);

        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        return new PokemonDTO(
                savedPokemon.getId(),
                savedPokemon.getApelido(),
                savedPokemon.getNivel(),
                savedPokemon.getEspecie(),
                savedPokemon.getEspecie().getEstatistica(),
                savedPokemon.getNivelAmizade(),
                savedPokemon.getPokedex()
        );
    }

    @Transactional
    public List<Pokedex> listarTodasPokedex() {
        return pokedexRepository.findAll();
    }
    @Transactional
    public Pokedex listarPokedex(Long id, String nome) {
        if (id != null) {
            return pokedexRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pokédex com ID " + id + " não encontrada"));
        } else if (nome != null) {
            return pokedexRepository.findByNome(nome)
                    .orElseThrow(() -> new RuntimeException("Pokédex com nome " + nome + " não encontrada"));
        } else {
            throw new RuntimeException("Pelo menos um parâmetro (ID ou nome) deve ser fornecido");
        }
    }
}
