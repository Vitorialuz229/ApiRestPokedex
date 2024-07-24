package ufg.inf.cs.ApiRestPokedex.controller.especieController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;
import ufg.inf.cs.ApiRestPokedex.service.especie.EspecieService;

import java.io.IOException;
import java.util.List;

@RestController
public class EspecieControlador {

    @Autowired
    private EspecieService especieService;

    /*
    @GetMapping("pokemon/")
    public List<Especie> listAllPokemon() {
        try {
            return especieService.listAllEspecieNamesAndTypes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch Pokémon list: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public String addPokemonByName(@RequestParam String name) {
        try {
            especieService.addEspecieByName(name);
            return "Pokemon added successfully!";
        } catch (IOException e) {
            return "Failed to add Pokémon: " + e.getMessage();
        }
    }

    @GetMapping("/details/{name}")
    public Especie getPokemonDetails(@PathVariable String name) {
        try {
            return especieService.getEspecieDetailsByName(name);
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch Pokémon details: " + e.getMessage());
        }
    }*/
}
