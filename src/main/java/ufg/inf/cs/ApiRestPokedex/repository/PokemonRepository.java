package ufg.inf.cs.ApiRestPokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
