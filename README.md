# CoursesAPI-DesafioBackEnd 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

## Sobre o desafio

 Nesse desafio você desenvolverá uma API fictícia para uma empresa de cursos de programação, onde em um primeiro momento, você deverá utilizar o CRUD, para criação de cursos.
> 
 A API deve conter as seguintes funcionalidades:
> 
> - Criação de um novo curso
> - Listagem de todos os cursos
> - Atualização de um curso pelo `id`
> - Remover um curso pelo `id`
> 
 ### Rotas e regras de negócio
> 
 Antes das rotas, vamos entender qual a estrutura (propriedades) que uma task deve ter:
> 
> - `id` - Identificador único de cada curso
> - `name` - Nome do curso
> - `category` - Categoria do curso
> - `Active` - Define se o curso está ativo ou não
> - `created_at` - Data de quando o curso foi criado.
> - `updated_at` - Deve ser sempre alterado para a data de quando o curso for atualizada.

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou a versão mais recente de `Java JDK 17`
- Você tem uma máquina `<Windows / Linux / Mac>`.
- O projeto está utilizando `PostgresSQL` como banco de dados. Sendo necessário o `FlyWay` para migração de dados.
- Para ([instalar PostgresSQL])(https://www.postgresql.org/download/) você pode clicar no link.

## 🚀 Instalando CoursesAPI-DesafioBackEnd

Para instalar o Cryptography-DesafioBackEnd, siga estas etapas:
Git:
```
git clone https://github.com/Ital023/DesafioRocketSeat-SpringBoot.git
```

## ☕ Usando CoursesAPI-DesafioBackEnd

Para usar CoursesAPI-DesafioBackEnd, siga estas etapas:

```
Rotas:

- POST - /cursos
    
    Deve ser possível criar um curso no banco de dados, enviando os campos `name` e `category` por meio do `body` da requisição.
    
    Ao criar um curso, os campos: `id`, `created_at`   e `updated_at` devem ser preenchidos automaticamente, conforme a orientação das propriedades acima.
    
- GET - /cursos
    
    Deve ser possível listar todas os cursos salvos no banco de dados.
    
    Também deve ser possível realizar uma busca, filtrando os cursos pelo `name` e `category`
    
- PUT - /cursos/:id`
    
    Deve ser possível atualizar um curso pelo `id`.
    
    No `body` da requisição, deve receber somente o `name` e/ou `category` para serem atualizados.
    
    Se for enviado somente o `name`, significa que o `category` não pode ser atualizado e vice-versa. Além disso `active` for informado nessa rota, ele deverá ser ignorado, pois a rota que deverá fazer essa atualização, é a de patch.
    
- DELETE - /cursos/:id`
    
    Deve ser possível remover um curso pelo `id`.
    
- PATCH - /cursos/:id/active

```

## 🤝 Colaboradores

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Ital023" title="Github do Ítalo Miranda">
        <img src="https://avatars.githubusercontent.com/u/113559117?v=4" width="100px;" alt="Foto do Ítalo Miranda no GitHub"/><br>
        <sub>
          <b>Ítalo Miranda</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
