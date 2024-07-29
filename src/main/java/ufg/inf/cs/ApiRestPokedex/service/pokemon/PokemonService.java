package ufg.inf.cs.ApiRestPokedex.service.pokemon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinadorDTO;
import ufg.inf.cs.ApiRestPokedex.adapter.PokemonAdapter;
import ufg.inf.cs.ApiRestPokedex.entity.*;
import ufg.inf.cs.ApiRestPokedex.exception.ResourceNotFoundException;
import ufg.inf.cs.ApiRestPokedex.repository.especie.EspecieRepository;
import ufg.inf.cs.ApiRestPokedex.repository.pokemon.PokemonRepository;
import ufg.inf.cs.ApiRestPokedex.repository.treinador.TreinadorRepository;

import java.io.IOException;
import java.util.Arrays;
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
    private TreinadorRepository treinadorRepository;

    private PokemonAdapter pokemonAdapter;

    private PokemonService pokemonService;

    private final Random random = new Random();

    // URL base da PokéAPI
    private static final String POKEAPI_BASE_URL = "https://pokeapi.co/api/v2";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Retorna um Pokémon específico com base no ID e ID do treinador.
     *
     * @param treinadorId ID do treinador.
     * @param pokemonId   ID do Pokémon.
     * @return O Pokémon correspondente ao ID fornecido.
     */
    public PokemonDTO getPokemonByIdAndTreinador(Long treinadorId, Long pokemonId) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador not found"));

        Pokemon pokemon = pokemonRepository.findByIdAndTreinador(pokemonId, treinador)
                .orElseThrow(() -> new RuntimeException("Pokemon not found for this trainer"));

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

    /**
     * Retorna um Pokémon específico com base no nome e ID do treinador.
     *
     * @param treinadorId ID do treinador.
     * @param nome        Nome do Pokémon.
     * @return O Pokémon correspondente ao nome fornecido.
     */
    public PokemonDTO getPokemonByNameAndTreinador(Long treinadorId, String nome) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador not found"));

        Pokemon pokemon = pokemonRepository.findByApelidoAndTreinador(nome, treinador)
                .orElseThrow(() -> new RuntimeException("Pokemon not found with name: " + nome + " for this trainer"));

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

    /**
     * Treina um Pokémon, aumentando seu nível e nível de amizade.
     *
     * @param treinadorId  ID do treinador.
     * @param pokemonId    ID do Pokémon a ser treinado.
     * @param treinadorDTO Dados de treinamento.
     * @return O Pokémon atualizado como DTO.
     */
    @Transactional
    public PokemonDTO treinarPokemon(Long treinadorId, Long pokemonId, TreinadorDTO treinadorDTO) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador not found"));

        Pokemon pokemon = pokemonRepository.findByIdAndTreinador(pokemonId, treinador)
                .orElseThrow(() -> new RuntimeException("Pokemon not found for this trainer"));

        int novoNivel = pokemon.getNivel() + 1;
        // String novoNivelAmizade = calcularNovoNivelAmizade(pokemon.getNivelAmizade(), treinadorDTO.getAumentoAmizade());

        pokemon.setNivel(novoNivel);
        // pokemon.setNivelAmizade(novoNivelAmizade);

        Pokemon pokemonAtualizado = pokemonRepository.save(pokemon);

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

    /**
     * Lista todos os Pokémons.
     *
     * @return Lista de todos os Pokémons como DTOs.
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

    /**
     * Evolui um Pokémon, se possível.
     *
     * @param pokemonId   ID do Pokémon a ser evoluído.
     * @param treinadorId ID do treinador que possui o Pokémon.
     * @return O Pokémon atualizado como DTO.
     */
    @Transactional
    public PokemonDTO evoluirPokemon(Long pokemonId, int treinadorId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

        // Verificar se o Pokémon pertence ao treinador
        if (pokemon.getTreinador().getId() != treinadorId) {
            throw new RuntimeException("Pokémon não pertence ao treinador");
        }

        Especie novaEspecie = obterNovaEspecie(pokemon);
        if (novaEspecie == null) {
            throw new RuntimeException("Pokémon não pode evoluir");
        }

        pokemon.setEspecie(novaEspecie);
        Pokemon pokemonAtualizado = pokemonRepository.save(pokemon);

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

    /**
     * Obtém a nova espécie para evolução do Pokémon.
     *
     * @param pokemon O Pokémon a ser evoluído.
     * @return Nova espécie para o Pokémon ou null se não houver evolução.
     */
    private Especie obterNovaEspecie(Pokemon pokemon) {
        String especieUrl = pokemon.getEspecie().getUrl();
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(especieUrl, JsonNode.class);
        JsonNode especieNode = response.getBody();

        // Obter a URL da cadeia de evolução
        String evolucaoChainUrl = especieNode.path("evolution_chain").path("url").asText();
        ResponseEntity<JsonNode> evolucaoResponse = restTemplate.getForEntity(evolucaoChainUrl, JsonNode.class);
        JsonNode evolucaoChainNode = evolucaoResponse.getBody();

        // Encontrar a nova espécie na cadeia de evolução
        return encontrarNovaEspecieNaCadeia(evolucaoChainNode, pokemon.getNivel());
    }

    /**
     * Encontra a nova espécie na cadeia de evolução com base no nível do Pokémon.
     *
     * @param evolucaoChainNode Resposta da cadeia de evolução como JsonNode.
     * @param nivel             Nível do Pokémon.
     * @return Nova espécie ou null se não houver evolução.
     */
    private Especie encontrarNovaEspecieNaCadeia(JsonNode evolucaoChainNode, int nivel) {
        JsonNode chain = evolucaoChainNode.path("chain");

        while (chain != null && !chain.path("species").isMissingNode()) {
            JsonNode speciesNode = chain.path("species");
            String speciesName = speciesNode.path("name").asText();
            int minLevel = 0;

            for (JsonNode evolutionDetail : chain.path("evolves_to").get(0).path("evolution_details")) {
                minLevel = evolutionDetail.path("min_level").asInt();
                if (nivel >= minLevel) {
                    Especie especie = especieRepository.findByName(speciesName);
                    if (especie != null) {
                        return especie;  // Retorna Especie ao invés de EspecieDTO
                    }
                }
            }

            chain = chain.path("evolves_to").isEmpty() ? null : chain.path("evolves_to").get(0);
        }

        return null;
    }

    /**
     * Retorna todos os Pokémons pertencentes a um treinador.
     *
     * @param treinadorId ID do treinador.
     * @return Lista de Pokémons do treinador como DTOs.
     */
    @Transactional
    public List<PokemonDTO> getPokemonsDoTreinador(Long treinadorId) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        List<Pokemon> pokemons = treinador.getPokemons().stream().collect(Collectors.toList());

        return pokemons.stream()
                .map(pokemon -> pokemonAdapter.toPokemonDTO(pokemon))
                .collect(Collectors.toList());
    }

    /**
     * Salva um Pokémon primário da API externa para um treinador específico.
     *
     * @param treinadorId ID do treinador para quem o Pokémon será salvo.
     * @return Detalhes do Pokémon salvo representado por um objeto {@link PokemonDTO}.
     * @throws IOException Se houver um erro ao acessar a API externa ou ao processar os dados.
     * @throws ResourceNotFoundException Se o treinador com o ID fornecido não for encontrado, se
     *         a lista de Pokémon não for encontrada na API externa, ou se nenhum Pokémon primário for encontrado.
     */
    @Transactional
    public PokemonDTO salvarPokemonPrimario(Long treinadorId) throws IOException {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Treinador não encontrado"));

        // URL para obter a lista de todos os Pokémon
        String url = "https://pokeapi.co/api/v2/pokemon?limit=1000";
        PokemonAdapter response = restTemplate.getForObject(url, PokemonAdapter.class);

        if (response == null || response.getResults() == null) {
            throw new ResourceNotFoundException("Lista de Pokémon não encontrada na API");
        }

        List<String> nomesPrimarios = Arrays.asList("squirtle", "bulbasaur", "charmander");

        PokemonAdapter.PokemonResult selectedPokemon = response.getResults().stream()
                .filter(result -> nomesPrimarios.contains(result.getName().toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum Pokémon primário encontrado na lista"));

        // Obtendo detalhes do Pokémon
        PokemonAdapter.PokemonDetails details;
        try {
            details = restTemplate.getForObject(selectedPokemon.getUrl(), PokemonAdapter.PokemonDetails.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter detalhes do Pokémon " + selectedPokemon.getName(), e);
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setApelido(details.getName());
        pokemon.setNivel(1);
        pokemon.setNivelAmizade("0");

        pokemon.setTipos(details.getTypes().stream()
                .map(typeInfo -> typeInfo.getType().getName())
                .collect(Collectors.toList()));
        pokemon.setPeso(details.getWeight());
        pokemon.setAltura(details.getHeight());

        Pokedex pokedex = new Pokedex();
        pokedex.setId(1L);
        pokemon.setPokedex(pokedex);

        Especie especie = new Especie();
        especie.setId(1L);
        pokemon.setEspecie(especie);

        Estatistica estatistica = new Estatistica();
        estatistica.setId(1);
        pokemon.setEstatistica(estatistica);

        pokemon.setTreinador(treinador);

        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        return new PokemonDTO(savedPokemon.getId(), savedPokemon.getApelido(), savedPokemon.getNivel(), savedPokemon.getPokedex(), savedPokemon.getEspecie(), savedPokemon.getEstatistica(), savedPokemon.getNivelAmizade(), savedPokemon.getTreinador());
    }

    /**
     * Salva todos os Pokémons da API externa para um treinador específico.
     *
     * @param treinadorId ID do treinador para quem os Pokémons serão salvos.
     * @return Lista de todos os Pokémons salvos representados por objetos {@link PokemonDTO}.
     * @throws IOException Se houver um erro ao acessar a API externa ou ao processar os dados.
     */
    @Transactional
    public List<PokemonDTO> salvarTodosPokemonsParaTreinador(Long treinadorId) throws IOException {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Treinador não encontrado"));

        // Obtendo a lista de Pokémon da API
        String listUrl = "https://pokeapi.co/api/v2/pokemon/?limit=1000";
        PokemonAdapter response = restTemplate.getForObject(listUrl, PokemonAdapter.class);

        if (response == null || response.getResults() == null) {
            throw new ResourceNotFoundException("Lista de Pokémon não encontrada na API");
        }

        List<PokemonDTO> savedPokemonDTOs = response.getResults().stream()
                .map(pokemonResult -> {
                    try {
                        // Obtendo detalhes do Pokémon específico
                        PokemonAdapter.PokemonDetails details = restTemplate.getForObject(pokemonResult.getUrl(), PokemonAdapter.PokemonDetails.class);

                        if (details == null || details.getName() == null) {
                            throw new ResourceNotFoundException("Detalhes do Pokémon " + pokemonResult.getName() + " não encontrados na API");
                        }

                        Pokemon pokemon = new Pokemon();
                        pokemon.setApelido(details.getName());
                        pokemon.setNivel(1);
                        pokemon.setNivelAmizade("0");

                        pokemon.setTipos(details.getTypes().stream()
                                .map(typeInfo -> typeInfo.getType().getName())
                                .collect(Collectors.toList()));
                        pokemon.setPeso(details.getWeight());
                        pokemon.setAltura(details.getHeight());

                        Pokedex pokedex = new Pokedex();
                        pokedex.setId(1L);
                        pokemon.setPokedex(pokedex);

                        Especie especie = new Especie();
                        especie.setId(1L);
                        pokemon.setEspecie(especie);

                        Estatistica estatistica = new Estatistica();
                        estatistica.setId(1);
                        pokemon.setEstatistica(estatistica);

                        pokemon.setTreinador(treinador);

                        Pokemon savedPokemon = pokemonRepository.save(pokemon);

                        PokemonAdapter adapter = new PokemonAdapter();
                        return adapter.toPokemonDTO(savedPokemon);
                    } catch (RestClientException e) {
                        throw new RuntimeException("Erro ao salvar Pokémon " + pokemonResult.getName(), e);
                    }
                })
                .collect(Collectors.toList());

        return savedPokemonDTOs;
    }

    /**
     * Salva um Pokémon específico por nome para um treinador.
     *
     * @param treinadorId ID do treinador para quem o Pokémon será salvo.
     * @param pokemonNome Nome do Pokémon a ser salvo.
     * @return Detalhes do Pokémon salvo representado por um objeto {@link PokemonDTO}.
     * @throws IOException Se houver um erro ao acessar a API externa ou ao processar os dados.
     * @throws ResourceNotFoundException Se o treinador com o ID fornecido não for encontrado, se
     *         a lista de Pokémon não for encontrada na API externa, ou se o Pokémon com o nome fornecido
     *         não for encontrado.
     */
    @Transactional
    public PokemonDTO salvarPokemonPorNomeParaTreinador(Long treinadorId, String pokemonNome) throws IOException {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Treinador não encontrado"));

        // Obtendo a lista de Pokémon da API
        String listUrl = "https://pokeapi.co/api/v2/pokemon/?limit=1000";
        PokemonAdapter response = restTemplate.getForObject(listUrl, PokemonAdapter.class);

        if (response == null || response.getResults() == null) {
            throw new ResourceNotFoundException("Lista de Pokémon não encontrada na API");
        }

        PokemonAdapter.PokemonResult selectedPokemon = response.getResults().stream()
                .filter(pokemon -> pokemonNome.equalsIgnoreCase(pokemon.getName()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Pokémon " + pokemonNome + " não encontrado na lista"));

        PokemonAdapter.PokemonDetails details = restTemplate.getForObject(selectedPokemon.getUrl(), PokemonAdapter.PokemonDetails.class);

        if (details == null || details.getName() == null) {
            throw new ResourceNotFoundException("Detalhes do Pokémon " + pokemonNome + " não encontrados na API");
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setApelido(details.getName());
        pokemon.setNivel(1);
        pokemon.setNivelAmizade("0");

        pokemon.setTipos(details.getTypes().stream()
                .map(typeInfo -> typeInfo.getType().getName())
                .collect(Collectors.toList()));
        pokemon.setPeso(details.getWeight());
        pokemon.setAltura(details.getHeight());

        Pokedex pokedex = new Pokedex();
        pokedex.setId(1L);
        pokemon.setPokedex(pokedex);

        Especie especie = new Especie();
        especie.setId(1L);
        pokemon.setEspecie(especie);

        Estatistica estatistica = new Estatistica();
        estatistica.setId(1);
        pokemon.setEstatistica(estatistica);

        pokemon.setTreinador(treinador);

        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        PokemonAdapter adapter = new PokemonAdapter();
        return adapter.toPokemonDTO(savedPokemon);
    }

    /**
     * Remove um Pokémon da lista de um treinador específico.
     *
     * @param treinadorId ID do treinador.
     * @param pokemonId ID do Pokémon a ser removido.
     * @throws ResourceNotFoundException Se o treinador ou o Pokémon não for encontrado.
     */
    @Transactional
    public void removerPokemonDoTreinador(Long treinadorId, int pokemonId) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Treinador não encontrado"));

        Pokemon pokemon = pokemonRepository.findById((long) pokemonId)
                .orElseThrow(() -> new ResourceNotFoundException("Pokémon não encontrado"));

        if (!treinador.getPokemons().contains(pokemon)) {
            throw new ResourceNotFoundException("O Pokémon não está na lista do treinador");
        }

        treinador.getPokemons().remove(pokemon);
        treinadorRepository.save(treinador);
    }

    /**
     * Atualiza o nível de amizade de um Pokémon com um treinador específico.
     *
     * @param pokemonId ID do Pokémon.
     * @param treinadorId ID do treinador.
     * @param aumento Quantidade de aumento na amizade.
     */
    @Transactional
    public void atualizarNivelAmizade(int pokemonId, Long treinadorId, int aumento) {
        // Encontrar o Pokémon e o treinador
        Pokemon pokemon = pokemonRepository.findById((long) pokemonId)
                .orElseThrow(() -> new ResourceNotFoundException("Pokémon não encontrado"));
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Treinador não encontrado"));

        if (!pokemon.getTreinador().getId().equals(treinadorId)) {
            throw new ResourceNotFoundException("O Pokémon não pertence ao treinador fornecido");
        }

        String novoNivelAmizade = calcularNovoNivelAmizade(pokemon.getNivelAmizade(), aumento);

        pokemon.setNivelAmizade(novoNivelAmizade);

        pokemonRepository.save(pokemon);
    }

    /**
     * Calcula o novo nível de amizade baseado no nível atual e no aumento fornecido.
     *
     * @param nivelAtual Nível de amizade atual.
     * @param aumento Quantidade de aumento na amizade.
     * @return Novo nível de amizade.
     */
    private String calcularNovoNivelAmizade(String nivelAtual, int aumento) {
        try {
            int nivelAtualInt = Integer.parseInt(nivelAtual);

            int novoNivelInt = nivelAtualInt + aumento;

            return String.valueOf(novoNivelInt);
        } catch (NumberFormatException e) {
            return String.valueOf(aumento);
        }
    }
}