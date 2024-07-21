package ufg.inf.cs.ApiRestPokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;

@Repository
public interface TreinadorRepository extends JpaRepository<Treinador, Long> {
}