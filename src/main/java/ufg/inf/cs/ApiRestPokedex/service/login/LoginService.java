package ufg.inf.cs.ApiRestPokedex.service.login;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.DTO.login.LoginDTO;
import ufg.inf.cs.ApiRestPokedex.entity.Login;
import ufg.inf.cs.ApiRestPokedex.repository.login.LoginRepository;

import java.util.Optional;

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

    @Transactional
    public boolean deletarLogin(String loginId) {

        try {
            loginRepository.deleteById(loginId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Login atualizarLogin(String id, LoginDTO loginDTO) {
        Optional<Login> loginOpt = loginRepository.findById(id);

        if (loginOpt.isPresent()) {
            Login login = loginOpt.get();
            login.setUsername(loginDTO.getUsername());
            login.setSenha(loginDTO.getSenha());
            login.setEmail(loginDTO.getEmail());

            //Se houver atualização de Treinador, incluir aqui

            return loginRepository.save(login);
        } else {
            return null;
        }
    }
}
