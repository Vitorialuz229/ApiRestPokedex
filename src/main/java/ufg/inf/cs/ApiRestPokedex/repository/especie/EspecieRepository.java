package ufg.inf.cs.ApiRestPokedex.repository.especie;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.model.Especie.Especie;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
