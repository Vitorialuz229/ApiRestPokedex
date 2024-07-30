package ufg.inf.cs.ApiRestPokedex.service.pokedex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import ufg.inf.cs.ApiRestPokedex.repository.pokedex.PokedexRepository;

@Service
public class PokedexService {

    @Autowired
    private PokedexRepository pokedexRepository;

    public Pokedex cadastrarPokedex(Pokedex pokedex) {
        return pokedexRepository.save(pokedex);
    }
}
