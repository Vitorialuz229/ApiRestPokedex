package ufg.inf.cs.ApiRestPokedex.controller;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ufg.inf.cs.ApiRestPokedex.DTO.LoginDTO;
import ufg.inf.cs.ApiRestPokedex.DTO.TreinadorDTO;
import ufg.inf.cs.ApiRestPokedex.entity.Login;
import ufg.inf.cs.ApiRestPokedex.entity.Pokedex;
import ufg.inf.cs.ApiRestPokedex.entity.Treinador;
import ufg.inf.cs.ApiRestPokedex.response.CadastroLoginResponse;
import ufg.inf.cs.ApiRestPokedex.response.LoginResponse;
import ufg.inf.cs.ApiRestPokedex.service.LoginService;

@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    private ResponseEntity  login (@RequestBody LoginDTO loginDTO) {

        Login login = loginService.validaLogin(loginDTO.getUsername(), loginDTO.getSenha());

        if (login == null) {
            return ResponseEntity.status(401).body("Falha no Login");
        }

        if(login.getUsername().equals(loginDTO.getUsername()) && login.getSenha().equals(loginDTO.getSenha())) {

            LoginResponse response = LoginResponse.builder()
                    .mensagem("Login Realizado com Sucesso!")
                    .treinador(login.getTreinador()) // observado que o objeto treinador e login esta com loop infinito devido ao relacionamento das tabelas
                    .build();

            return ResponseEntity.ok().body(response);
        }
        else{
            return ResponseEntity.status(401).body("Falha no Login");
        }
    }

    @PostMapping("login/cadastro")
    private ResponseEntity cadastrarTreinador(@RequestBody LoginDTO loginDTO) {

        if (loginDTO == null) {
            return ResponseEntity.status(400).body("Informe os dados para cadastro");
        }

        Login login = new Login();
        login.setUsername(loginDTO.getUsername());
        login.setSenha(loginDTO.getSenha());
        login.setEmail(loginDTO.getEmail());

//        Treinador treinador = new Treinador();
//        treinador.setLogin(login);
//        treinador.setNome(loginDTO.getTreinadorDTO().getNome());
//        treinador.setDataNascimento(loginDTO.getTreinadorDTO().getDataNascimento());
//        treinador.setNivel(loginDTO.getTreinadorDTO().getNivel());

        Login response =  loginService.cadastrarTreinador(login);

        if (response != null) {

            CadastroLoginResponse cadastroLoginResponse = CadastroLoginResponse.builder()
                    .mensagem("Login cadstrado com Sucesso!")
                    .login(response)
                    .build();

            return ResponseEntity.status(201).body(cadastroLoginResponse);
        }

        return ResponseEntity.status(400).body("Erro ao cadastrar Treinador");
    }

}
