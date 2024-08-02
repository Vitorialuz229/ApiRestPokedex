package ufg.inf.cs.ApiRestPokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.TreinadorPokemon;
import java.util.Optional;

public interface TreinadorPokemonRepository extends JpaRepository<TreinadorPokemon, Long> {
    Optional<TreinadorPokemon> findByTreinadorIdAndPokemonId(Long treinadorId, Long pokemonId);
}
