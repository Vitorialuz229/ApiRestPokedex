package ufg.inf.cs.ApiRestPokedex.DTO.treinador;

public class TreinadorSeguidoDTO {

        private Long id;
        private String nome;
        private Boolean favorito;

        public TreinadorSeguidoDTO(Long id, String nome, Boolean favorito) {
            this.id = id;
            this.nome = nome;
            this.favorito = favorito;
        }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public Boolean getFavorito () {
        return favorito;
    }

    public void setFavorito (Boolean favorito) {
        this.favorito = favorito;
    }
}
