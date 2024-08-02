# ApiRestPokedex

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

## Descrição

ApiRestPokedex é uma API RESTful para gerenciar informações sobre Pokémon. Este projeto utiliza MariaDB como banco de dados para armazenar os dados da Pokédex.

## Índice

- [Pré-requisitos](#Pré-requisitos)
- [Instalando o Projeto](#Instalando o Projeto)

## Pré-requisitos

Para rodar esse projeto você precisa ter os seguintes pré-requisitos instalados na sua máquina:

1. **Java JDK 17**:
    - Baixe e instale o JDK 17 a partir do [site oficial do OpenJDK](https://openjdk.java.net/install/).
    - Configure a variável de ambiente `JAVA_HOME` para apontar para o diretório de instalação do JDK.

2. **Apache Maven**:
    - Baixe e instale o Maven a partir do [site oficial do Apache Maven](https://maven.apache.org/download.cgi).
    - Configure a variável de ambiente `M2_HOME` e adicione o diretório `bin` do Maven ao `PATH`.

3. **MariaDB**:
    - Baixe e instale o MariaDB a partir do [link de download do MariaDB](https://mariadb.com/kb/en/postdownload/mariadb-server-11-4-2/).
    - Configure o MariaDB com um usuário e senha apropriados.
    - Crie o banco de dados necessário para o seu projeto.

<h2 id="how-to-use"> 🚀 Instalando o projeto</h2>

1. **Clone o Repositório**:
```bash
# Clone o repositório
$ git clone https://github.com/Vitorialuz229/ApiRestPokedex

# Acesse-o
$ cd ApiRestPokedex
```
2. Instale as Dependências com o Maven:

Agora, dentro do IntelliJ, vamos instalar as depedências com o Maven

<img width="300px" src="./.github/instalar-deps.png">

3. Execute o Projeto:

E por fim, entre no arquivo da classe `ApiRestPokedexApplication` para executar o projeto

<img width="300px" src="./.github/executar.png">


### Diagrama de Classe

[![Diagrama de Classe](./.github/Diagrama_de_Classes_API_POKEDEX.png)](https://lucid.app/lucidchart/e4d44f7a-2830-4879-8902-49ae3d3c04a3/edit?viewport_loc=1421%2C-225%2C4098%2C1755%2C0_0&invitationId=inv_9c3b9e71-c796-4c57-aa59-088ffa42a6c0)

Clique na imagem acima para ver o diagrama completo no Lucidchart.

### Contribuidoras

Esse projeto existe graças às pessoas que contribuíram.

<ul style="list-style-type: none; padding: 0; margin: 0; display: flex; flex-wrap: wrap; gap: 20px;">
    <li style="text-align: center;">
        <a href="https://github.com/thayliny">
            <img src="https://avatars.githubusercontent.com/u/55805454?v=4" alt="Thayliny" style="width: 100px; height: 100px; border-radius: 50%; object-fit: cover;" />
            <br> Thayliny
        </a>
    </li>
    <li style="text-align: center;">
        <a href="https://github.com/TanyRM">
            <img src="https://avatars.githubusercontent.com/u/127350142?v=4" alt="TanyRM" style="width: 100px; height: 100px; border-radius: 50%; object-fit: cover;" />
            <br> TanyRM
        </a>
    </li>
    <li style="text-align: center;">
        <a href="https://github.com/Vitorialuz229">
            <img src="https://avatars.githubusercontent.com/u/110250731?v=4" alt="Vitorialuz229" style="width: 100px; height: 100px; border-radius: 50%; object-fit: cover;" />
            <br> Vitorialuz229
        </a>
    </li>
    <li style="text-align: center;">
        <a href="https://github.com/RaquelDiasES">
            <img src="https://avatars.githubusercontent.com/u/107322375?v=4" alt="RaquelDiasES" style="width: 100px; height: 100px; border-radius: 50%; object-fit: cover;" />
            <br> RaquelDiasES
        </a>
    </li>
</ul>
