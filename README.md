# Projeto Spring Quiz

## Descrição
Este projeto Spring Quiz tem como objetivo criar uma plataforma para certificação de conhecimentos em diversas tecnologias. Os usuários podem responder a perguntas sobre uma determinada tecnologia e receber uma certificação com base em suas respostas.

## Estrutura do Projeto
O projeto está dividido em módulos principais:

### 1. Módulo de Estudantes (`modules.students`)
   - **Pacote `useCases`**: Contém a lógica de negócios relacionada às respostas dos estudantes.
     - `StudentCertificationAnswersUseCase`: Classe responsável por processar as respostas dos estudantes, calcular as pontuações e gerar certificações.

   - **Pacote `controllers`**: Contém os controladores relacionados aos estudantes.
     - `StudentController`: Controlador que lida com as solicitações relacionadas aos estudantes, como verificar se um estudante já possui certificação e processar as respostas dos estudantes.

### 2. Módulo de Perguntas (`modules.questions`)
   - **Pacote `controller`**: Contém o controlador relacionado às perguntas.
     - `QuestionController`: Controlador que lida com as solicitações relacionadas às perguntas, como obter perguntas com base em uma tecnologia específica.

### 3. Módulo de Certificações (`modules.certifications`)
   - **Pacote `useCases`**: Contém a lógica de negócios relacionada ao ranking das certificações.
     - `Top10RankingUseCase`: Classe responsável por recuperar o top 10 do ranking de certificações.

   - **Pacote `controllers`**: Contém o controlador relacionado ao ranking das certificações.
     - `RankingController`: Controlador que lida com as solicitações relacionadas ao ranking de certificações, como obter o top 10.

## Tecnologias Utilizadas
- Java
- Spring Framework (Spring Boot)
- Spring Data JPA
- RESTful API

## Como Executar
1. Certifique-se de ter o ambiente Java e Spring configurado.
2. Clone o repositório.
3. Execute a aplicação Spring Boot.
4. Executar o Seed para implementar todas as perguntas no banco.

## Endpoints Disponíveis
- `/students/verifyIfHasCertification`: Verifica se um estudante já possui certificação.
- `/students/certification/answer`: Processa as respostas dos estudantes e gera certificações.
- `/questions/technology/{technology}`: Obtém perguntas com base em uma tecnologia específica.
- `/ranking/top10`: Obtém o top 10 do ranking de certificações.

Projeto desenvolvido durante o evento da Rocketseat de Java.
