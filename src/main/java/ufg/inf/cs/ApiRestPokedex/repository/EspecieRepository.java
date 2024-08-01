package ufg.inf.cs.ApiRestPokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;

import java.util.Optional;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
    Optional<Especie> findByNameIgnoreCase(String name);
}
