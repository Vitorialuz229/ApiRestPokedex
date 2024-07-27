package ufg.inf.cs.ApiRestPokedex.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.DTO.ItemDTO;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;
import ufg.inf.cs.ApiRestPokedex.repository.TreinadorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinadorService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public void comprarItem (Long treinadorId, Long itemId) {
        Treinador treinador = treinadorRepository.findById (treinadorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));
        Item item = itemRepository.findById (itemId)
                .orElseThrow (() -> new RuntimeException ("Item não encontrado"));

        // Criar uma nova instância de Item e associar ao treinador
        Item novoItem = new Item ();
        novoItem.setId (item.getId ());
        novoItem.setNome (item.getNome ());
        novoItem.setDescricao (item.getDescricao ());
        novoItem.setPreco (item.getPreco ());
        novoItem.setTreinador (treinador);

        // Adicionar o novo item à lista de itens do treinador
        treinador.getItens ().add (novoItem);

        // Salvar o treinador e o novo item
        treinadorRepository.save (treinador);
        itemRepository.save (novoItem);
    }

    @Transactional
    public void consumirItem(Long treinadorId, Long itemId) {
        Treinador treinador = treinadorRepository.findById(treinadorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow (() -> new RuntimeException ("Item não encontrado"));

        treinador.getItens().remove(item);
        treinadorRepository.save(treinador);
    }

    public List<ItemDTO> getItensDoTreinador (Long treinadorId) {
        Treinador treinador = treinadorRepository.findById (treinadorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        return treinador.getItens ().stream ()
                .map (item -> new ItemDTO (item.getId(), item.getNome (), item.getDescricao ()))
                .collect (Collectors.toList ());
    }

    @Transactional
    public void subirNivel (Long treinadorId) {
        Treinador treinador = treinadorRepository.findById (treinadorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        treinador.setNivel (treinador.getNivel () + 1);
        treinadorRepository.save(treinador);
    }
}
