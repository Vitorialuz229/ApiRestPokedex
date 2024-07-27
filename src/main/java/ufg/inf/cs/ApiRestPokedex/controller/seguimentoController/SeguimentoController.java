package ufg.inf.cs.ApiRestPokedex.controller.seguimentoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.treinador.TreinadorSeguidoDTO;
import ufg.inf.cs.ApiRestPokedex.service.seguimento.SeguimentoService;

import java.util.List;

@Controller
@RequestMapping("/seguindo")
public class SeguimentoController {

    @Autowired
    private SeguimentoService seguimentoService;

    // Seguir um novo treinador
    @PostMapping("/{seguidorId}/seguir")
    public ResponseEntity<String> seguirTreinador (@PathVariable Long seguidorId, @RequestParam String seguidoNome) {
        seguimentoService.seguirTreinador (seguidorId, seguidoNome);
        return ResponseEntity.ok ("Você está seguindo o usuario " + seguidoNome);
    }

    // Ver uma lista de todos os treinadores seguidos pelo treinador
    @GetMapping("/{seguidorId}/seguidos")
    public ResponseEntity<List<TreinadorSeguidoDTO>> getSeguidos (@PathVariable Long seguidorId) {
        List<TreinadorSeguidoDTO> seguidos = seguimentoService.getSeguidos (seguidorId);
        return ResponseEntity.ok (seguidos);
    }

    // Alterar a situação de uma relação entre seguidor e seguido
    @PatchMapping("/{seguidorId}")
    public ResponseEntity<String> favoritarSeguido (@PathVariable Long seguidorId, @RequestParam Long seguidoId) {
        boolean favorito = seguimentoService.alterarFavorito (seguidorId, seguidoId);
        if (favorito) {
            return ResponseEntity.ok ("Agora esse treinador está favoritado!");
        } else {
            return ResponseEntity.ok ("Esse treinador não está mais favoritado!");
        }
    }
    //deixarDeSeguirTreinador
    @DeleteMapping("/{seguidorId}/deixarDeSeguir")
    public ResponseEntity<String> deixarDeSeguirTreinador(@PathVariable Long seguidorId, @RequestParam Long seguidoId) {
        seguimentoService.deixarDeSeguirTreinador(seguidorId, seguidoId);
        return ResponseEntity.ok("Você deixou de seguir o treinador com ID " + seguidoId);
    }
}
