# Servidor de Gestão de Eventos

Este é um servidor Node.js para um sistema de gestão de eventos. Ele fornece uma API para gerenciar eventos, colaboradores e autenticação de usuários.

## Tecnologias Utilizadas

- **Node.js**: Ambiente de execução JavaScript server-side.
- **Express.js**: Framework web para Node.js que simplifica a criação de aplicativos e APIs.
- **SQLite3**: Banco de dados SQL embutido para armazenamento de dados.
- **JWT (JSON Web Tokens)**: Método para token de autenticação.
- **Crypto**: Módulo para geração de chaves de criptografia.
- **Cors**: Middleware para habilitar o CORS (Cross-Origin Resource Sharing).

## Funcionalidades

- Autenticação de usuários através de email e senha.
- Criação, atualização e listagem de eventos.
- Busca do próximo evento para um colaborador específico.
- Registro e listagem de colaboradores.
- Atualização de senha do colaborador.

## Endpoints da API

- `POST /login`: Autentica um usuário e retorna um token JWT.
- `POST /atualizarEvento`: Atualiza um evento existente.
- `GET /proximoEvento/:colaboradorId`: Retorna o próximo evento para um colaborador.
- `POST /eventos`: Cria um novo evento.
- `GET /listar-eventos`: Lista todos os eventos.
- `GET /eventos-com-colaboradores`: Lista todos os eventos com os colaboradores associados.
- `GET /rota-protegida`: Rota protegida que requer autenticação JWT.
- `POST /registro-colaboradores`: Registra um novo colaborador.
- `GET /colaboradores`: Lista todos os colaboradores.
- `POST /atualizarSenha`: Atualiza a senha de um colaborador.

## Como Executar

1. Instale as dependências:
   
2. Inicie o servidor:

## Configuração do Banco de Dados

O servidor utiliza um banco de dados SQLite. O arquivo de banco de dados pode ser encontrado em `Projeto_InterdisciplinarDB.db`.

## Configuração do Ambiente

O servidor pode ser configurado através de variáveis de ambiente, incluindo a porta em que ele será executado e a chave secreta para geração de tokens JWT.

- `PORT`: Porta em que o servidor será executado (padrão: 4000).
- `SECRET_KEY`: Chave secreta para geração de tokens JWT.

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir um pull request para sugerir melhorias ou correções.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

# Frontend do Aplicativo de Agendamento de Eventos

Esta seção da documentação é dedicada a explicar o frontend do aplicativo de agendamento de eventos. O frontend é responsável por fornecer uma interface de usuário intuitiva e amigável para os usuários, permitindo que eles visualizem, interajam e gerenciem eventos de forma eficiente.

## Tela Principal

### Lista de Eventos

A tela principal exibe uma lista de eventos agendados, permitindo que os usuários visualizem rapidamente os eventos próximos. Cada evento na lista exibe informações como título, data, hora e local.

![Tela Principal - Lista de Eventos]
(screenshot_lista_eventos.png)
#### Funcionalidades:

- **Visualizar Detalhes do Evento**: Os usuários podem tocar em um evento na lista para visualizar mais detalhes, como descrição, participantes e horários.
- **Agendar Novo Evento**: Um botão na parte inferior da tela permite que os usuários agendem um novo evento, levando-os à tela de criação de eventos.

## Tela de Detalhes do Evento

### Detalhes do Evento

Esta tela exibe detalhes completos sobre um evento específico, incluindo título, descrição, data, hora, local e lista de participantes.

![Tela de Detalhes do Evento](screenshot_detalhes_evento.png)

#### Funcionalidades:

- **Editar Evento**: Se o usuário for o organizador do evento, um botão de edição permite que ele atualize os detalhes do evento.
- **Excluir Evento**: Um botão de exclusão permite que o organizador do evento exclua o evento.
- **Compartilhar Evento**: Os usuários podem compartilhar detalhes do evento com outros por meio de diferentes aplicativos de mensagens ou redes sociais.

## Tela de Criação/Edição de Evento

### Formulário de Criação/Edição

Nesta tela, os usuários podem criar um novo evento preenchendo um formulário com informações como título, descrição, data, hora, local e lista de participantes.

![Tela de Criação/Edição de Evento](screenshot_criacao_evento.png)

#### Funcionalidades:

- **Validação de Campos**: Os campos obrigatórios são destacados e a validação é realizada para garantir que todos os dados necessários sejam fornecidos.
- **Seleção de Data e Hora**: Os usuários podem selecionar facilmente a data e a hora do evento usando controles intuitivos.
- **Adição de Participantes**: Um campo de pesquisa permite que os usuários adicionem participantes ao evento, buscando entre os contatos ou digitando os endereços de email.
- **Notificações**: Opções para configurar notificações automáticas para lembretes de eventos podem ser incluídas no formulário.

Este modelo de documentação fornece uma visão geral do frontend do aplicativo de agendamento de eventos, descrevendo as principais telas, funcionalidades e fluxos de usuário. Ele pode ser expandido com mais detalhes e capturas de tela, conforme necessário.

