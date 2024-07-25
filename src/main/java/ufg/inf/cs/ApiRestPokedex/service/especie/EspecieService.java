package ufg.inf.cs.ApiRestPokedex.service.especie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.repository.especie.EspecieRepository;

@Service
public class EspecieService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon";

    @Autowired
    private EspecieRepository especieRepository;
}
