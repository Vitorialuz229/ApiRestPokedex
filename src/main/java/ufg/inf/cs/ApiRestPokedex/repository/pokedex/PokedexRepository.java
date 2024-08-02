package ufg.inf.cs.ApiRestPokedex.repository.pokedex;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import java.util.Optional;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {
    Optional<Pokedex> findByIdAndNome(Long id, String nome);
    Optional<Pokedex> findById(Long id);
    Optional<Pokedex> findByNome(String nome);
}