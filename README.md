# Integração com o Firebase

Este é um projeto Android desenvolvido em Java que oferece funcionalidades de autenticação por meio do Firebase Authentication e gerenciamento de produtos utilizando o Firestore Database. O aplicativo permite que os usuários se cadastrem, façam login e redefinam suas senhas. Além disso, implementa operações de CRUD (Criar, Ler, Atualizar e Deletar), possibilitando aos usuários gerenciar uma lista de produtos de maneira fácil e eficiente.

<br>

## Autenticação
[![Login.jpg](https://i.postimg.cc/bw2KbxXd/Login.jpg)](https://postimg.cc/4YsLRhxT)
[![Cadastro.jpg](https://i.postimg.cc/9Xn8pDY9/Cadastro.jpg)](https://postimg.cc/CdbsMLtM)
[![Redefinir-senha.jpg](https://i.postimg.cc/MHCY8j9r/Redefinir-senha.jpg)](https://postimg.cc/ctc3R6yQ)

## CRUD
[![CRUD.jpg](https://i.postimg.cc/3Jhsp48Z/CRUD.jpg)](https://postimg.cc/yWfpCNSJ)
[![CRUD-Create.jpg](https://i.postimg.cc/d3w57C2k/CRUD-Create.jpg)](https://postimg.cc/sQHYqv0s)
[![CRUD-Read.jpg](https://i.postimg.cc/cJ3hXhTt/CRUD-Read.jpg)](https://postimg.cc/XZV9vwRn)

## Funcionalidades

- Cadastro de novos usuários
- Login de usuários existentes
- Redefinição de senha por e-mail
- Adicionar novos produtos
- Listar produtos existentes
- Atualizar informações de produtos
- Deletar produtos

## Tecnologias Utilizadas

- **Android Studio**: IDE utilizada para o desenvolvimento do aplicativo.
- **Java**: Linguagem de programação empregada na construção do projeto.
- **Android SDK**: Conjunto de ferramentas de desenvolvimento para Android.
- **Firebase Authentication**: Serviço que permite a autenticação de usuários.
- **Firebase Firestore**: Banco de dados NoSQL para armazenamento e gerenciamento de dados.
- **XML**: Linguagem de marcação utilizada para definir os layouts da interface do usuário.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter os seguintes itens instalados:

- [Android Studio](https://developer.android.com/studio) (versão mais recente)
- Uma conta no [Firebase](https://firebase.google.com/)

## Configuração do Firebase

1. Crie um novo projeto no [Firebase Console](https://console.firebase.google.com/).
2. Adicione um aplicativo Android ao seu projeto no Firebase.
3. Baixe o arquivo `google-services.json` e coloque-o na pasta `app/` do seu projeto Android.
4. Ative a autenticação por e-mail e senha nas configurações do Firebase Authentication.

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/CoracaoDeLeao/Integracao-com-o-Firebase.git
2. Abra o projeto no Android Studio.
3. Sincronize o Gradle.
4. Execute o aplicativo em um dispositivo ou emulador.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.
