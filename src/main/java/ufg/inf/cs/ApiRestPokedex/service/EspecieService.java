package ufg.inf.cs.ApiRestPokedex.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ufg.inf.cs.ApiRestPokedex.adapter.EspecieAdapter;
import ufg.inf.cs.ApiRestPokedex.entity.*;
import ufg.inf.cs.ApiRestPokedex.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspecieService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon";

    private final RestTemplate restTemplate;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private TiposRepository tiposRepository;

    @Autowired
    private MovimentosRepository movimentosRepository;

    @Autowired
    private EstatisticaRepository estatisticaRepository;

    @Autowired
    private HabilidadesRepository habilidadesRepository;

    public EspecieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Transactional
    public EspecieAdapter getPokemonData(String name) {
        String url = BASE_URL + "/" + name;
        return restTemplate.getForObject(url, EspecieAdapter.class);
    }

    @Transactional
    public void savePokemonData(String name) {
        EspecieAdapter especieAdapter = getPokemonData(name);

        Especie especie = new Especie();
        especie.setName(especieAdapter.getName());
        especie.setWeight(especieAdapter.getWeight());
        especie.setHeight(especieAdapter.getHeight());
        especie.setBase_experience(especieAdapter.getBase_experience());

        // Mapeia e salva Tipos
        List<Tipos> tipos = especieAdapter.getTypes().stream().map(type -> {
            Tipos tipo = new Tipos();
            tipo.setName(type.getType().getName());
            return tipo;
        }).collect(Collectors.toList());
        especie.setTipos(tiposRepository.saveAll(tipos));

        // Mapeia e salva Movimentos
        List<Movimentos> movimentos = especieAdapter.getMoves().stream().map(move -> {
            Movimentos movimento = new Movimentos();
            movimento.setName(move.getMove().getName());
            return movimento;
        }).collect(Collectors.toList());
        especie.setMovimentos(movimentosRepository.saveAll(movimentos));

        // Mapeia e salva Habilidades
        List<Habilidades> habilidades = especieAdapter.getAbilities().stream().map(ability -> {
            Habilidades habilidade = new Habilidades();
            habilidade.setName(ability.getAbility().getName());
            return habilidade;
        }).collect(Collectors.toList());
        especie.setHabilidades(habilidadesRepository.saveAll(habilidades));

        // Mapeia e salva Estatistica
        Estatistica estatistica = new Estatistica();
        estatistica.setSaude(especieAdapter.getStats().stream().filter(stat -> "hp".equals(stat.getStat().getName())).findFirst().orElseThrow().getBase_stat());
        estatistica.setAtaque(especieAdapter.getStats().stream().filter(stat -> "attack".equals(stat.getStat().getName())).findFirst().orElseThrow().getBase_stat());
        estatistica.setDefesa(especieAdapter.getStats().stream().filter(stat -> "defense".equals(stat.getStat().getName())).findFirst().orElseThrow().getBase_stat());
        estatistica.setAtaqueEspecial(especieAdapter.getStats().stream().filter(stat -> "special-attack".equals(stat.getStat().getName())).findFirst().orElseThrow().getBase_stat());
        estatistica.setDefesaEspecial(especieAdapter.getStats().stream().filter(stat -> "special-defense".equals(stat.getStat().getName())).findFirst().orElseThrow().getBase_stat());
        estatistica.setVelocidade(especieAdapter.getStats().stream().filter(stat -> "speed".equals(stat.getStat().getName())).findFirst().orElseThrow().getBase_stat());
        especie.setEstatistica(estatisticaRepository.save(estatistica));

        // Salva a Especie
        especieRepository.save(especie);
    }

    @Transactional
    public List<Especie> listarTodasEspecies() {
        return especieRepository.findAll();
    }
}
