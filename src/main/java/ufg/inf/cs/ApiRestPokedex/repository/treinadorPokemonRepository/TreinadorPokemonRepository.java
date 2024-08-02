package ufg.inf.cs.ApiRestPokedex.repository.treinadorPokemonRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ufg.inf.cs.ApiRestPokedex.entity.TreinadorPokemon;

import java.util.List;
import java.util.Optional;

public interface TreinadorPokemonRepository extends JpaRepository<TreinadorPokemon, Long> {
    Optional<TreinadorPokemon> findByTreinadorIdAndPokemonId(Long treinadorId, Long pokemonId);

    @Query("SELECT DISTINCT tp.nivelAmizade AS nivelAmizade, p.id AS pokemonId, tp.treinador.id AS treinadorId, p.apelido AS apelido, p.nivel AS nivel, p.especie AS especie, p.especie.estatistica AS estatistica " +
            "FROM TreinadorPokemon tp " +
            "JOIN tp.pokemon p " +
            "WHERE tp.treinador.id = :treinadorId")
    List<Object[]> findPokemonsByTreinadorId(@Param("treinadorId") Long treinadorId);
}
