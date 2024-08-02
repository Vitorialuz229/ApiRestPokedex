package ufg.inf.cs.ApiRestPokedex.repository.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByPokedex(Pokedex pokedex);

    @Query("SELECT p FROM Pokemon p " +
            "JOIN p.pokedex px " +
            "WHERE px = :pokedex AND LOWER(p.apelido) LIKE LOWER(CONCAT('%', :apelidoPokemon, '%'))")
    List<Pokemon> findByPokedexAndApelidoContainingIgnoreCase(@Param("pokedex") Pokedex pokedex,
                                                              @Param("apelidoPokemon") String apelidoPokemon);
}
