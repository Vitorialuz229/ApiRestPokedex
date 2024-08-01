package ufg.inf.cs.ApiRestPokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Tipos;

public interface TiposRepository extends JpaRepository<Tipos, Long> {
}
