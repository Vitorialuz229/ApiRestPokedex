package ufg.inf.cs.ApiRestPokedex.service.seguimento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinadorSeguidoDTO;
import ufg.inf.cs.ApiRestPokedex.entity.Seguimento;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;
import ufg.inf.cs.ApiRestPokedex.repository.seguidor.SeguimentoRepository;
import ufg.inf.cs.ApiRestPokedex.repository.treinador.TreinadorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguimentoService {

    @Autowired
    private SeguimentoRepository seguimentoRepository;

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Transactional
    public void seguirTreinador (Long seguidorId, String seguidoNome) {
        Treinador seguidor = treinadorRepository.findById (seguidorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));
        Treinador seguido = treinadorRepository.findByNome (seguidoNome)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        Seguimento seguimento = new Seguimento ();
        seguimento.setSeguidor (seguidor);
        seguimento.setSeguido (seguido);
        seguimento.setFavorito (false);

        seguimentoRepository.save (seguimento);
    }

    public List<TreinadorSeguidoDTO> getSeguidos (Long seguidorId) {
        Treinador seguidor = treinadorRepository.findById (seguidorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        return seguimentoRepository.findBySeguidor (seguidor).stream ()
                .map (seguimento -> new TreinadorSeguidoDTO (
                        seguimento.getSeguido ().getId (),
                        seguimento.getSeguido ().getNome (),
                        seguimento.getFavorito ()))
                .collect (Collectors.toList ());
    }

    @Transactional
    public boolean alterarFavorito (Long seguidorId, Long seguidoId) {
        Treinador seguidor = treinadorRepository.findById (seguidorId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        Treinador seguido = treinadorRepository.findById (seguidoId)
                .orElseThrow (() -> new RuntimeException ("Treinador não encontrado"));

        List<Seguimento> seguidos = seguimentoRepository.findBySeguidor (seguidor);
        boolean novoFavorito = false;

        for (Seguimento seguimento : seguidos) {
            if (seguimento.getSeguido ().getId ().equals (seguidoId)) {
                seguimento.setFavorito (!seguimento.getFavorito ());
                novoFavorito = seguimento.getFavorito ();
                break;
            }
        }
        return novoFavorito;
    }
}
