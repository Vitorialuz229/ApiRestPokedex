package ufg.inf.cs.ApiRestPokedex.repository.login;

//TODo conexão com o Banco de Dados

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufg.inf.cs.ApiRestPokedex.entity.Item;
import ufg.inf.cs.ApiRestPokedex.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByUsernameAndSenha(String username, String senha);
}
