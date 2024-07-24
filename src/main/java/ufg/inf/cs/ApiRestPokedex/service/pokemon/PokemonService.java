package ufg.inf.cs.ApiRestPokedex.service.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinamentoDTO;
import ufg.inf.cs.ApiRestPokedex.adapter.PokemonAdapter;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;
import ufg.inf.cs.ApiRestPokedex.repository.especie.EspecieRepository;
import ufg.inf.cs.ApiRestPokedex.repository.pokemon.PokemonRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final Random random = new Random();

    public List<Pokemon> getPokemonApi() throws IOException {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=1000";
        PokemonAdapter response = restTemplate.getForObject(url, PokemonAdapter.class);

        List<Pokemon> pokemons = new ArrayList<>();

        return pokemons;
    }

    public PokemonDTO getPokemonById(Long pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokemon not found"));
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getApelido(),
                pokemon.getNivel(),
                pokemon.getPokedex(),
                pokemon.getEspecie(),
                pokemon.getEstatistica(),
                pokemon.getNivelAmizade(),
                pokemon.getTreinador()
        );
    }

    public PokemonDTO getPokemonByName(String nome) {
        Pokemon pokemon = pokemonRepository.findByApelido(nome)
                .orElseThrow(() -> new RuntimeException("Pokemon not found with name: " + nome));
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getApelido(),
                pokemon.getNivel(),
                pokemon.getPokedex(),
                pokemon.getEspecie(),
                pokemon.getEstatistica(),
                pokemon.getNivelAmizade(),
                pokemon.getTreinador()
        );
    }



    public void deletePokemon(Long pokemonId) {
        pokemonRepository.deleteById(pokemonId);
    }

    public PokemonDTO treinarPokemon(Long pokemonId, TreinamentoDTO treinamentoDTO) {
        // Obter o Pokémon pelo ID
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokemon not found"));

        // Atualizar nível e base de experiência
        int novoNivel = pokemon.getNivel() + 1;
        int novaBaseExperiencia = pokemon.getBaseExperience() + treinamentoDTO.getExperienciaAdicional();

        // Atualizar nível de amizade
        String novoNivelAmizade = calcularNovoNivelAmizade(pokemon.getNivelAmizade(), treinamentoDTO.getAumentoAmizade());

        // Aplicar as atualizações no Pokémon
        pokemon.setNivel(novoNivel);
        pokemon.setBaseExperience(novaBaseExperiencia);
        pokemon.setNivelAmizade(novoNivelAmizade);

        // Salvar o Pokémon atualizado
        Pokemon pokemonAtualizado = pokemonRepository.save(pokemon);

        // Retornar o Pokémon atualizado como DTO
        return new PokemonDTO(
                pokemonAtualizado.getId(),
                pokemonAtualizado.getApelido(),
                pokemonAtualizado.getNivel(),
                pokemonAtualizado.getPokedex(),
                pokemonAtualizado.getEspecie(),
                pokemonAtualizado.getEstatistica(),
                pokemonAtualizado.getNivelAmizade(),
                pokemonAtualizado.getTreinador()
        );
    }

    // Método para calcular o novo nível de amizade
    private String calcularNovoNivelAmizade(String nivelAtual, int aumento) {
        // Lógica para calcular o novo nível de amizade
        // Pode ser uma simples concatenação ou um cálculo mais complexo
        // Exemplo: incrementar o valor numérico do nível de amizade
        int nivelAtualInt = Integer.parseInt(nivelAtual);
        int novoNivelInt = nivelAtualInt + aumento;
        return String.valueOf(novoNivelInt);
    }
/*
    private void savePokemon(String url) {
        EspecieResponse response = restTemplate.getForObject(url, EspecieResponse.class);

        if (response != null) {
            Especie especie = new Especie();
            especie.setName(response.getName());
            especie.setTypes(response.getTypes());
            especie.setHeight(response.getHeight());
            especie.setWeight(response.getWeight());
            especie.setBaseExperience(response.getBaseExperience());
            especie.setVariation(response.getSpecies());
            especie.setGenero(response.getGenderRate() != -1);
            especie.setAttack(response.getMoves());

            especie = especieRepository.save(especie);

            Pokemon pokemon = new Pokemon();
            pokemon.setApelido(response.getName());
            pokemon.setNivel(random.nextInt(100)); // Define um nível aleatório entre 0 e 100
            pokemon.setEspecie(especie);
            // Defina as outras propriedades do Pokémon conforme necessário
            pokemonRepository.save(pokemon);
        }
    }
*/
    public List<PokemonDTO> listAll() {
        return pokemonRepository.findAll()
                .stream()
                .map(pokemon -> new PokemonDTO(
                        pokemon.getId(),
                        pokemon.getApelido(),
                        pokemon.getNivel(),
                        pokemon.getPokedex(),
                        pokemon.getEspecie(),
                        pokemon.getEstatistica(),
                        pokemon.getNivelAmizade(),
                        pokemon.getTreinador()
                ))
                .collect(Collectors.toList());
    }

    public PokemonDTO evoluirPokemon(Long pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokemon not found"));

        // Verificar se o Pokémon pode evoluir
        Especie novaEspecie = obterNovaEspecie(pokemon);
        if (novaEspecie == null) {
            throw new RuntimeException("Pokemon cannot evolve");
        }

        // Atualizar a espécie do Pokémon
        pokemon.setEspecie(novaEspecie);

        // Salvar o Pokémon atualizado
        Pokemon pokemonAtualizado = pokemonRepository.save(pokemon);

        // Retornar o Pokémon atualizado como DTO
        return new PokemonDTO(
                pokemonAtualizado.getId(),
                pokemonAtualizado.getApelido(),
                pokemonAtualizado.getNivel(),
                pokemonAtualizado.getPokedex(),
                pokemonAtualizado.getEspecie(),
                pokemonAtualizado.getEstatistica(),
                pokemonAtualizado.getNivelAmizade(),
                pokemonAtualizado.getTreinador()
        );
    }

    private Especie obterNovaEspecie(Pokemon pokemon) {
        // Lógica para determinar a nova espécie com base na evolução
        // Pode ser baseada no nível, itens ou outros critérios
        // Aqui está um exemplo básico:
        if (pokemon.getNivel() >= 16) { // Supondo que Pokémon evolua no nível 16
            return especieRepository.findByName("EspecieEvoluida");
        }
        return null;
    }
}