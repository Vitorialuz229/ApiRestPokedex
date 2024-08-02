package ufg.inf.cs.ApiRestPokedex.controller.especie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.adapter.EspecieAdapter;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;
import ufg.inf.cs.ApiRestPokedex.service.especie.EspecieService;

import java.util.List;

@RestController
@RequestMapping("/especie")
public class EspecieController {

    private final EspecieService especieService;

    @Autowired
    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping("/{name}")
    public EspecieAdapter getPokemonData(@PathVariable String name) {
        return especieService.getPokemonData(name);
    }

    @GetMapping("/")
    public List<Especie> listarEspecies() {
        return especieService.listarTodasEspecies();
    }

    @PostMapping("/save")
    public String saveEspecie(@RequestParam String name) {
        try {
            especieService.savePokemonData(name);
            return "Espécie salva com sucesso!";
        } catch (Exception e) {
            // Log the exception and return an error message
            e.printStackTrace();
            return "Erro ao salvar a espécie: " + e.getMessage();
        }
    }
}