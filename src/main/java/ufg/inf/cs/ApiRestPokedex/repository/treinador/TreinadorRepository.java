package ufg.inf.cs.ApiRestPokedex.repository.treinador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreinadorRepository extends JpaRepository<Treinador, Long> {
    Optional<Treinador> findByNome (String nome);
}