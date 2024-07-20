package ufg.inf.cs.ApiRestPokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.service.ItemService;
import ufg.inf.cs.ApiRestPokedex.service.TreinadorService;

import java.util.List;

@RestController
@RequestMapping("/Loja")
public class LojaController {

    @Autowired
    private final ItemService itemService;

    @Autowired
    private TreinadorService treinadorService;

    public LojaController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/itens")
    public List<Item> getItens() {
        return itemService.getAllItems();
    }

    @GetMapping("/itens/{id}")
    public Item verItem(@RequestParam Long id) {
        Item item = itemService.getItemById(id);
        System.out.println(item);
        return item;
    }

    @PostMapping("/{treinadorId}/comprarItem")
    public String comprarItem(@PathVariable Long treinadorId, @RequestParam Long itemId) {
        treinadorService.comprarItem(treinadorId, itemId);
        return "Item comprado com sucesso!";
    }
}
