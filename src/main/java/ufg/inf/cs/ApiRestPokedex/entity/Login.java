package ufg.inf.cs.ApiRestPokedex.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "login")
    private Treinador treinador;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(unique=true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String senha;

}
