package ufg.inf.cs.ApiRestPokedex.repository.movimentoRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.inf.cs.ApiRestPokedex.entity.Movimentos;

public interface MovimentosRepository extends JpaRepository<Movimentos, Long> {
}
