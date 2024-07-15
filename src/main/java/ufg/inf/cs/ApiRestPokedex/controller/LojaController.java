package ufg.inf.cs.ApiRestPokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/Loja")
public class LojaController {

    @Autowired
    private final ItemService itemService;

    public LojaController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/itens")
    public List<Item> getItens() {
        return itemService.getAllItems();
    }
}
