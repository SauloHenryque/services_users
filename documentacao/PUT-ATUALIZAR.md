# **Descrição:** Atualiza um user a partir do seu código de identificação.

**Description (en-US):** Update an user by the identifier code

## **Arquitetura**

**Nome da Tag:** user

**Path:** PUT /api/user/{id}

**Nome do Resource:** br.com.saulo.user.web.UserResource

**Nome do Serviço:** br.com.saulo.user.servicos.UserServico

**Nome do Método:** atualizarUser

## **Requisição**

### **Nome do Request:** br.com.saulo.user.dto.persists.UserPersist

|Parâmetro | Obrigatório | Descrição | Description (en-US) | Tipo parâmetro | Tipo de dados | Exemplo | Validador |
|---|:---:|---|---|:---:|:---:|---|---|
| id | Sim | Código identificador da user | user identifier | Path | Long  | 1 |
| nome | Sim | Nome User| user name | Body | String | "Usuario teste" | Máximo: 100 |
| email | Sim | Email User| user email | Body | String | "email@teste.com" | Máximo: 100 |
| password | Sim | Password user | Password user | Body | String | "12345678912345" | Máximo: 100 |


### **Exemplo Request:**
```
/api/user/1
```
```json
{
  "nome": "Usuario teste",
  "email": "email@teste.com",
  "password": "12345678912345"
}
```

## **Resposta**

### **Nome do Response:** br.com.saulo.user.dto.responses.UserResponse

|Parâmetro | Descrição | Description (en-US) | Tipo de dados | Exemplo |
|---|---|---|:---:|---|
| id | Código identificador da user | user identifier | Long | 1 | 
| nome | Nome User | user name | String | "Usuario teste" | 
| email | Email User | user email | String | "email@teste.com" | 
| password | Password user | Password user | String | "kajdfaiosdfjowweowejwie" |

**Código status da resposta HTTP: 200 - Ok**

### **Exemplo Response:**
```json
{
  "id": 1,
  "nome": "Teste 1",
  "email": "email@teste.com"
  "password": "kajdfaiosdfjowweowejwie"
}
```

## **Documentos e referência**

**Tabelas:** user

## **Detalhes de implementação**

**Pré-requisitos**
* Existir user para o id informado
* Cumprir as validações de entrada

**Fluxo de execução**

1. Consultar a user pelo id
2. Verificar se a user não é nulo  
2.1. Se for nulo, retornar exceção de registro não encontrado  
3. Converter o objeto persist na entidade user
4. Verificar existência de user já registrado com o email informado  
4.1. Se existir, retornar exceção de exemplo com email já cadastrado 
5. Salvar entidade
6. Converter a entidade no response
7. Retornar o response

## **Casos de teste**

| Onde | Teste | HTTP Status | Resultado |
| --- | --- | :---: | --- |
| Service | Quando atualiza a user | 200 | Ok |
| Service | Quando a user não existe | 404 | Not Found |
| Service | Quando atualiza user com email já cadastrado | 400 | Bad Request |
| DTO | Quando a quantidade de caracteres do atributo nome for maior que 255 |  400 | Bad Request |
| DTO | Quando a quantidade de caracteres do atributo email for maior que 100 |  400 | Bad Request |
| DTO | Quando a quantidade de caracteres do atributo password for maior que 100 |  400 | Bad Request |
| DTO | Quando o atributo password não existir | 400 | Bad Request |
| DTO | Quando o atributo email não existir | 400 | Bad Request |
| DTO | Quando o atributo nome não existir | 400 | Bad Request |

## **Exceções**

| HTTP Status | Chave | Mensagem | Message (en-US) |
|---|---|---|---|
| 404 | REGISTRO_NAO_ENCONTRADO | Nenhum registro encontrado para essa solicitação | No register found for this request |
| 400 | EMAIL_JA_CADASTRADO | Email já cadastrado | Email already registered |