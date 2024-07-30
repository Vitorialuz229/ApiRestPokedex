package ufg.inf.cs.ApiRestPokedex.controller.loja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.service.item.ItemService;
import ufg.inf.cs.ApiRestPokedex.service.treinador.TreinadorService;

import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    private final ItemService itemService;

    @Autowired
    private TreinadorService treinadorService;

    // Construtor para injeção de dependência do ItemService
    public LojaController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Listar todos os itens à venda na loja.
     *
     * @return Lista de itens disponíveis.
     */
    @GetMapping("/itens")
    public List<Item> getItens() {
        return itemService.getAllItems();
    }

    /**
     * Ver informações de um item específico.
     *
     * @param id ID do item.
     * @return Detalhes do item.
     */
    @GetMapping("/itens/{id}")
    public Item verItem(@RequestParam Long id) {
        Item item = itemService.getItemById(id);
        System.out.println(item);
        return item;
    }

    /**
     * Comprar um item da loja.
     *
     * @param treinadorId ID do treinador que está comprando o item.
     * @param itemId ID do item a ser comprado.
     * @return Mensagem de sucesso.
     */
    @PostMapping("/{treinadorId}/comprarItem")
    public String comprarItem(@PathVariable Long treinadorId, @RequestParam Long itemId) {
        treinadorService.comprarItem(treinadorId, itemId);
        return "Item comprado com sucesso!";
    }
}
