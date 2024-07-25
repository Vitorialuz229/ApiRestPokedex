package ufg.inf.cs.ApiRestPokedex.repository.pokedex;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {
    Pokedex findByTreinadorId (Long treinador);
}
