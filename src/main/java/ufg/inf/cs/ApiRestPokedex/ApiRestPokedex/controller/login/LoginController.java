package ufg.inf.cs.ApiRestPokedex.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.inf.cs.ApiRestPokedex.DTO.login.LoginDTO;
import ufg.inf.cs.ApiRestPokedex.controller.treinador.TreinadorController;
import ufg.inf.cs.ApiRestPokedex.entity.Login;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;
import ufg.inf.cs.ApiRestPokedex.response.CadastroLoginResponse;
import ufg.inf.cs.ApiRestPokedex.response.LoginResponse;
import ufg.inf.cs.ApiRestPokedex.service.login.LoginService;
import ufg.inf.cs.ApiRestPokedex.service.pokedex.PokedexService;
import ufg.inf.cs.ApiRestPokedex.service.pokemon.PokemonService;
import ufg.inf.cs.ApiRestPokedex.service.treinador.TreinadorService;

import java.io.IOException;

@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private TreinadorService treinadorService;
    @Autowired
    private TreinadorController treinadorController;
    @Autowired
    private PokedexService pokedexService;
    @Autowired
    private PokemonService pokemonService;

    @PostMapping("/login")
    private ResponseEntity  login (@RequestBody LoginDTO loginDTO) {

        Login login = loginService.validaLogin(loginDTO.getUsername(), loginDTO.getSenha());

        if (login == null) {
            return ResponseEntity.status(401).body("Falha no Login");
        }

        if(login.getUsername().equals(loginDTO.getUsername()) && login.getSenha().equals(loginDTO.getSenha())) {

            LoginResponse response = LoginResponse.builder()
                    .mensagem("Login Realizado com Sucesso!")
                    .login(login)
                    .treinador(null) // observado que o objeto treinador e login esta com loop infinito devido ao relacionamento das tabelas
                    .build();

            return ResponseEntity.ok().body(response);
        }
        else{
            LoginResponse response = LoginResponse.builder()
                    .mensagem("Falha no Login")
                    .login(null) // observado que o objeto treinador e login esta com loop infinito devido ao relacionamento das tabelas
                    .build();
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("login/cadastro")
    private ResponseEntity cadastrarTreinador(@RequestBody LoginDTO loginDTO) throws IOException {

        if (loginDTO == null) {
            return ResponseEntity.status(400).body("Informe os dados para cadastro");
        }

        Login login = new Login();
        login.setUsername(loginDTO.getUsername());
        login.setSenha(loginDTO.getSenha());
        login.setEmail(loginDTO.getEmail());

        Login response =  loginService.cadastrarTreinador(login);

        if (response != null) {
            loginDTO.getTreinadorDTO().setLoginId(response.getId());

            Treinador treinador = new Treinador();
            treinador.setLogin(response);
            treinador.setNome(loginDTO.getTreinadorDTO().getNome());
            treinador.setDataNascimento(loginDTO.getTreinadorDTO().getDataNascimento());
            treinador.setNivel(loginDTO.getTreinadorDTO().getNivel());

            Treinador responseTreinador = treinadorService.cadastrarTreinador(treinador);

            if (responseTreinador != null) {
                Pokedex pokedex = new Pokedex();
                pokedex.setTreinador(responseTreinador);

                Pokedex responsePokedex = pokedexService.cadastrarPokedex(pokedex);

                if (responsePokedex != null) {

                    CadastroLoginResponse cadastroLoginResponse = CadastroLoginResponse.builder()
                            .mensagem("Login cadstrado com Sucesso!")
                            .login(response)
                            .treinador(responseTreinador)
                            .build();

                    return ResponseEntity.status(201).body(cadastroLoginResponse);
                }
            }
        }

        return ResponseEntity.status(400).body("Erro ao cadastrar Treinador");
    }

    @DeleteMapping("/login/{id}")
    private ResponseEntity deletarLogin(@PathVariable String id) {

        boolean excluido = loginService.deletarLogin(id);

        LoginResponse loginResponse;

        if (excluido) {
            loginResponse = LoginResponse.builder()
                    .mensagem("Login deletado com Sucesso!")
                    .build();
        } else {
            loginResponse = LoginResponse.builder()
                    .mensagem("Ocorreu um erro ao deletar login!")
                    .build();
        }

        return ResponseEntity.status(200).body(loginResponse);
    }

    @PutMapping("/login/{id}")
    private ResponseEntity<LoginResponse> atualizarLogin(@PathVariable String id, @RequestBody LoginDTO loginDTO) {

        Login loginAtualizado = loginService.atualizarLogin(id, loginDTO);

        if (loginAtualizado != null) {
            LoginResponse loginResponse = LoginResponse.builder()
                    .mensagem("Login atualizado com Sucesso!")
                    .login(loginAtualizado)
                    .build();
            return ResponseEntity.ok().body(loginResponse);
        } else {
            LoginResponse loginResponse = LoginResponse.builder()
                    .mensagem("Erro ao atualizar login!")
                    .build();
            return ResponseEntity.status(400).body(loginResponse);
        }
    }







}
