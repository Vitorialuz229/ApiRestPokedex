package ufg.inf.cs.ApiRestPokedex.service.treinador;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.DTO.item.ItemDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.pokemon.PokemonDTO;
import ufg.inf.cs.ApiRestPokedex.adapter.PokemonAdapter;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;
import ufg.inf.cs.ApiRestPokedex.repository.ItemRepository;
import ufg.inf.cs.ApiRestPokedex.repository.TreinadorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinadorService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private ItemRepository itemRepository;

    private PokemonAdapter pokemonAdapter;

    /**
     * Permite que um treinador compre um item.
     *
     * @param treinadorId ID do treinador que está comprando.
     * @param itemId ID do item a ser comprado.
     */
    @Transactional
    public void comprarItem (Long treinadorId, Long itemId) {
        Treinador treinador = treinadorRepository.findById (treinadorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));
        Item item = itemRepository.findById (itemId)
                .orElseThrow (() -> new RuntimeException ("Item não encontrado"));

        Item novoItem = new Item ();
        novoItem.setId (item.getId ());
        novoItem.setNome (item.getNome ());
        novoItem.setDescricao (item.getDescricao ());
        novoItem.setPreco (item.getPreco ());
        novoItem.setTreinador (treinador);

        treinador.getItens ().add (novoItem);

        treinadorRepository.save (treinador);
        itemRepository.save (novoItem);
    }

    /**
     * Permite que um treinador consuma um item.
     *
     * @param treinadorId ID do treinador que está consumindo o item.
     * @param itemId ID do item a ser consumido.
     */
    @Transactional
    public void consumirItem(Long treinadorId, Long itemId) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow (() -> new RuntimeException ("Item não encontrado"));

        treinador.getItens().remove(item);
        treinadorRepository.save(treinador);
    }

    /**
     * Retorna a lista de itens de um treinador.
     *
     * @param treinadorId ID do treinador.
     * @return Lista de itens do treinador como DTOs.
     */
    public List<ItemDTO> getItensDoTreinador (Long treinadorId) {
        Treinador treinador = treinadorRepository.findById (treinadorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        return treinador.getItens ().stream ()
                .map (item -> new ItemDTO (item.getId(), item.getNome (), item.getDescricao ()))
                .collect (Collectors.toList ());
    }

    /**
     * Aumenta o nível de um treinador.
     *
     * @param treinadorId ID do treinador.
     */
    @Transactional
    public void subirNivel (Long treinadorId) {
        Treinador treinador = treinadorRepository.findById (treinadorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        treinador.setNivel (treinador.getNivel () + 1);
        treinadorRepository.save(treinador);
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
}
