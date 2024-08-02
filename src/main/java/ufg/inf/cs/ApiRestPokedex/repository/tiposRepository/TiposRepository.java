package ufg.inf.cs.ApiRestPokedex.repository.tiposRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Tipos;

public interface TiposRepository extends JpaRepository<Tipos, Long> {
}
