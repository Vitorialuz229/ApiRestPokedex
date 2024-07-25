package ufg.inf.cs.ApiRestPokedex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.DTO.LoginDTO;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.entity.Login;
import ufg.inf.cs.ApiRestPokedex.repository.ItemRepository;
import ufg.inf.cs.ApiRestPokedex.repository.LoginRepository;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Login validaLogin(String username, String senha) {
        return loginRepository.findByUsernameAndSenha(username, senha);
    }

    public Login cadastrarTreinador(Login login) {
        return loginRepository.save(login);
    }
}
