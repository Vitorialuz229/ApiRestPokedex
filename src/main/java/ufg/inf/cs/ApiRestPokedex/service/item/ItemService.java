package ufg.inf.cs.ApiRestPokedex.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.repository.item.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Retorna todos os itens disponíveis na loja.
     *
     * @return Lista de todos os itens.
     */
    public List<Item> getAllItems () {
        return itemRepository.findAll ();
    }

    /**
     * Retorna um item específico com base no ID.
     *
     * @param id ID do item.
     * @return O item correspondente ao ID fornecido.
     */
    public Item getItemById (Long id) {
        return itemRepository.findById (id).orElse (null);
    }
}
