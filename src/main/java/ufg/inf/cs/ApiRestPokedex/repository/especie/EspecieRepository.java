package ufg.inf.cs.ApiRestPokedex.repository.especie;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Especie;

import java.util.List;


public interface EspecieRepository extends JpaRepository<Especie, Long> {
        List<Especie> findByNameIgnoreCase(String name);
}
