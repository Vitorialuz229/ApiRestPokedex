package ufg.inf.cs.ApiRestPokedex.controller.seguir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinadorSeguidoDTO;
import ufg.inf.cs.ApiRestPokedex.service.seguir.SeguimentoService;

import java.util.List;

@Controller
@RequestMapping("/seguindo")
public class SeguimentoController {

    @Autowired
    private SeguimentoService seguimentoService;

    /**
     * Seguir um novo treinador.
     *
     * @param seguidorId ID do seguidor.
     * @param seguidoNome Nome do treinador a ser seguido.
     * @return Mensagem de sucesso.
     */
    @PostMapping("/{seguidorId}/seguir")
    public ResponseEntity<String> seguirTreinador(@PathVariable Long seguidorId, @RequestParam String seguidoNome) {
        seguimentoService.seguirTreinador(seguidorId, seguidoNome);
        return ResponseEntity.ok("Você está seguindo o usuário " + seguidoNome); // Adicionado espaço
    }

    /**
     * Ver uma lista de todos os treinadores seguidos por um treinador específico.
     *
     * @param seguidorId ID do seguidor.
     * @return Lista de treinadores seguidos.
     */
    @GetMapping("/{seguidorId}/seguidos")
    public ResponseEntity<List<TreinadorSeguidoDTO>> getSeguidos(@PathVariable Long seguidorId) {
        List<TreinadorSeguidoDTO> seguidos = seguimentoService.getSeguidos(seguidorId);
        return ResponseEntity.ok(seguidos);
    }

    /**
     * Alterar a situação de uma relação entre seguidor e seguido (favoritar ou desfavoritar um treinador).
     *
     * @param seguidorId ID do seguidor.
     * @param seguidoId ID do treinador seguido.
     * @return Mensagem indicando se o treinador foi favoritado ou não.
     */
    @PatchMapping("/{seguidorId}/{seguidoId}")
    public ResponseEntity<String> favoritarSeguido(@PathVariable Long seguidorId, @PathVariable Long seguidoId) {
        boolean favorito = seguimentoService.alterarFavorito(seguidorId, seguidoId);
        if (favorito) {
            return ResponseEntity.ok("Agora esse treinador está favoritado!");
        } else {
            return ResponseEntity.ok("Esse treinador não está mais favoritado!");
        }
    }
}
