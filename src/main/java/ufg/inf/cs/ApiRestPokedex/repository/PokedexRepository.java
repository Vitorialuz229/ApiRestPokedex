package ufg.inf.cs.ApiRestPokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import java.util.Optional;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {
    Optional<Pokedex> findById(Long id);
    Optional<Pokedex> findByNome(String nome);
}
