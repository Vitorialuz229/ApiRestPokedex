package ufg.inf.cs.ApiRestPokedex.repository.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    // Busca Pokémon pelo apelido
    Optional<Pokemon> findByApelido(String apelido);
}
