package ufg.inf.cs.ApiRestPokedex.repository.seguir;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufg.inf.cs.ApiRestPokedex.entity.Seguimento;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;

import java.util.List;

@Repository
public interface SeguimentoRepository extends JpaRepository<Seguimento, Long> {
    List<Seguimento> findBySeguidor (Treinador seguidor);
    void deleteBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId);
}


/*public interface SeguimentoRepository extends JpaRepository<Seguimento, Long> {
    void deleteBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId);
}*/