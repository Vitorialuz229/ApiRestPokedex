package ufg.inf.cs.ApiRestPokedex.service.seguir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinadorSeguidoDTO;
import ufg.inf.cs.ApiRestPokedex.entity.Seguimento;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;
import ufg.inf.cs.ApiRestPokedex.repository.seguir.SeguimentoRepository;
import ufg.inf.cs.ApiRestPokedex.repository.treinador.TreinadorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguimentoService {

    @Autowired
    private SeguimentoRepository seguimentoRepository;

    @Autowired
    private TreinadorRepository treinadorRepository;

    /**
     * Permite que um treinador siga outro treinador.
     *
     * @param seguidorId ID do treinador que está seguindo.
     * @param seguidoNome Nome do treinador a ser seguido.
     */
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

    /**
     * Retorna a lista de treinadores que um treinador está seguindo.
     *
     * @param seguidorId ID do treinador que está seguindo.
     * @return Lista de treinadores seguidos.
     */
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

    /**
     * Altera o status de favorito de um treinador seguido.
     *
     * @param seguidorId ID do treinador que está seguindo.
     * @param seguidoId ID do treinador seguido.
     * @return Novo status de favorito.
     */
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

    /**
     * Permite que um treinador deixe de seguir outro treinador.
     *
     * @param seguidorId ID do treinador que está seguindo.
     * @param seguidoId ID do treinador a ser deixado de seguir.
     */
    public void deixarDeSeguirTreinador(Long seguidorId, Long seguidoId) {
        // Lógica para deixar de seguir um treinador
        seguimentoRepository.deleteBySeguidorIdAndSeguidoId(seguidorId, seguidoId);
    }
}
