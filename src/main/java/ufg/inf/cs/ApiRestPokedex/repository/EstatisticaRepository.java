package ufg.inf.cs.ApiRestPokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Estatistica;

public interface EstatisticaRepository extends JpaRepository<Estatistica, Long> {
}
