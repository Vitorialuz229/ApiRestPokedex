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

    public List<Pokedex> listarTodasPokedex() {
        return pokedexRepository.findAll();
    }
    @Transactional
    public Pokedex listarPokedex(Long id, String nome) {
        if (id != null && nome != null) {
            return pokedexRepository.findByIdAndNome(id, nome)
                    .orElseThrow(() -> new RuntimeException("Pokedex não encontrada!"));
        } else if (id != null) {
            return pokedexRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pokedex não encontrada!"));
        } else if (nome != null) {
            return pokedexRepository.findByNome(nome)
                    .orElseThrow(() -> new RuntimeException("Pokedex não encontrada!"));
        } else {
            throw new RuntimeException("Nenhum parâmetro fornecido!");
        }
    }
}