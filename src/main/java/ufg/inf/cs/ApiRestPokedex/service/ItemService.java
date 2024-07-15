package ufg.inf.cs.ApiRestPokedex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getItemByNome(String nome) {
        return itemRepository.findByNome(nome);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
