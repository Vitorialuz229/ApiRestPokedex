package crontollers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    //TODO exposição de endPoints

    @PostMapping("/login")
    private void login() {
        System.out.println("Login realizado com sucesso!");
    }
}
