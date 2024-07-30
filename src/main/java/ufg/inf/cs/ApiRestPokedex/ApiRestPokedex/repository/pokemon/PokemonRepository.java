package ufg.inf.cs.ApiRestPokedex.repository.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    Optional<Pokemon> findByIdAndTreinador(Long pokemonId, Treinador treinador);

    Optional<Pokemon> findByApelidoAndTreinador(String apelido, Treinador treinador);
}

