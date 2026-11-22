<h1 align="center">
  Registro de despesas
</h1>

<h4 align="center">Status: ✔ Concluído</h4>

---

# **Sumário**

- [**Sumário**](#sumário)
- [**Sobre o projeto**](#sobre-o-projeto)
- [**Clonando o projeto**](#clonando-o-projeto)
- [**Pré-requisitos**](#pre-requisitos)
- [**Endpoints**](#endpoints)
- [**Configuração do modelo lfm3-audio**](#configuração-do-modelo-lfm3-audio)
- [**Docker**](#docker)
- [**Tecnologias**](#tecnologias)

---

# **Sobre o projeto**

API inteligente de controle de despesas que utiliza modelos IA locais para persistência dos registros de despesas.
Os registros de despesas são gravados no banco MySQL através da API REST ou através da chamada dos modelos, por meio da passagem de áudio da despesa realizada.
A API utiliza o Ollama com as interfaces da OpenAI para utilizar o modelo `lfm3-audio:2b` para transcrever a mensagem e para sintetizar uma resposta, além do modelo `gemma4:e2b` para interpretação da mensagem transcrita e chamada das Tools do projeto.

---

# **Clonando o projeto**

Antes de iniciar é necessário que você já tenha a ferramenta [Git](https://git-scm.com/) instalada.

```bash
# Clone este repositório
$ git clone https://github.com/ViniciusHP/registro-despesas.git

# Acesse a pasta deste projeto por meio do terminal
$ cd registro-despesas
```

---

# **Pré-requisitos**

- Java 25
- Maven 3.10.0 ( também pode ser utilizado o Maven Wrapper que vem com o Spring )
- MySQL 9.6
- Ollama 0.40.16
- Docker 4.99.0
---

# **Endpoints**

## Criação de uma transação financeira por meio de áudio

Envia um arquivo de áudio no formato `.wav` e retorna o áudio processado no mesmo formato.

### Entpoint
`POST /transaction/ai`

### Cabeçalhos da Requisição

| Header | Valor | Obrigatório |
| :--- | :--- | :--- |
| `Content-Type` | `multipart/form-data` | Sim |

### Parâmetros (Form-Data)

| Campo | Tipo | Obrigatório | Descrição | Exemplo |
| :--- | :--- | :--- | :--- | :--- |
| `file` | `File` (Binary) | Sim | Arquivo de áudio a ser enviado. | `audio.wav` |

---

### Exemplo de Requisição (cURL)

```bash
curl -X POST "http://localhost:8081/transactions/ai" \
  -F "file=@./src/test/resources/audio/audio-1.wav" \
  --output resposta-1.wav
```

### Resposta

#### Sucesso (`200 OK`)
* **Content-Type:** `audio/wav`
* **Body:** Conteúdo binário do arquivo `.wav` gerado (salvo como `resposta.wav` no exemplo acima).

## Criação de uma transação financeira

Cria uma transação financeira através da API REST.

### Endpoint
`POST /transactions`

### Parâmetros (Body JSON)

```json
{
    "description": "Compra no mercadinho da esquina",
    "category": "ALIMENTAÇÃO",
    "amount": 125.00
}
```

### Resposta

### Sucesso (`201 CREATED`)
* **Body:**
```json
    {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "description": "Compra no mercadinho da esquina",
        "category": "ALIMENTAÇÃO",
        "amount": 125.00
    }
```

## Listar transações por categoria

Lista as transações a partir de uma categoria informada.

### Endpoint
`GET /transactions/{category}`

### Resposta

### Sucesso (`200 OK`)
* **Body:**
```json
[
    {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "description": "Compra no mercadinho da esquina",
        "category": "ALIMENTAÇÃO",
        "amount": 125.00
    },
    {
        "id": "23131123-e29b-41d4-a716-446655440000",
        "description": "Compra na padaria",
        "category": "ALIMENTAÇÃO",
        "amount": 55.00
    }
]
```

## Criação de uma categoria

Cadastra uma nova categoria no sistema.

#### Endpoint
`POST /category`


### Parâmetros (Body JSON)

```json
{
  "name": "ALIMENTAÇÃO"
}
```

### Resposta

### Sucesso (`201 CREATED`)
* **Body:**
```json
{
  "id": "23131111-e29b-41d4-a716-446655440000",
  "name": "ALIMENTAÇÃO"
}
```

## Listagem de todas categorias

Lista todas as categorias cadastradas.

#### Endpoint
`GET /category`

### Resposta

### Sucesso (`200 OK`)
* **Body:**
```json
[
  {
    "id": "23131111-e29b-41d4-a716-446655440000",
    "name": "ALIMENTAÇÃO"
  },
  {
    "id": "22221111-e29b-41d4-a716-446655440000",
    "name": "FARMÁCIA"
  }
]
```

---

# **Configuração do modelo lfm3-audio**

Para que a voz em português seja gerada de uma forma mais natural e que verbalize a frase corretamente, utilize o arquivo `Modelfile` para criar uma versão local com os parâmetros corretos. Por conta que estamos utilizando a interface de API da OpenAI, ela não possui os parâmetros configurados para esse modelo, sendo necessária essa configuração.

Para alterar aplicar os parâmetros para o `lfm3-audio:2b`, execute o seguinte comando no terminal, na pasta que está o arquivo `Modelfile`:

```bash
$ ollama create lfm3-audio-pt -f Modelfile
```

Após isso, alterar o arquivo `application.yaml` para utlizar para esse modelo:

```yaml
spring:
  ai:
    openai:
      audio:
        speech:
          options:
            model: lfm3-audio-pt
```

---

# **Docker**

Para construir toda a infraestrutura do projeto, utilize o arquivo `docker-compose.yaml`.  Nele já está incluso o build com a configuração do modelo lfm3-audio para português.
Comando para construir toda a infraestrutura do projeto:

```bash
$ docker compose up --build
```

---

# **Tecnologias**

Este projeto foi construído com as seguintes ferramentas/tecnologias:

- **[Docker](https://www.docker.com/)**
- **[Flyway](https://flywaydb.org/)**
- **[Gemma 4](https://deepmind.google/models/gemma/gemma-4/)**
- **[LFM3-Audio-2B](https://www.liquid.ai/models)**
- **[Lombok](https://projectlombok.org/)**
- **[MySQL](https://www.mysql.com/)**
- **[Ollama](https://ollama.com/)**
- **[Spring Boot](https://spring.io/projects/spring-boot)**
- **[Spring AI](https://spring.io/projects/spring-ai)**
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**