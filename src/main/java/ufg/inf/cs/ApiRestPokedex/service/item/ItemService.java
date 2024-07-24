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

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }
}
