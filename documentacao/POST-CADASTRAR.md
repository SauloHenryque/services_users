#**Descrição:** Cadastra user.

**Description (en-US):** Save user.

## **Arquitetura**

**Nome da Tag:** user

**Path:** POST /api/user

**Nome do Resource:** br.com.saulo.user.web.UserResource

**Nome do Serviço:** br.com.saulo.user.servicos.UserServico

**Nome do Método:** salvarUser

## **Requisição**

### **Nome do Request:** br.com.saulo.user.dto.persists.UserPersist

|Parâmetro | Obrigatório | Descrição | Description (en-US) | Tipo parâmetro | Tipo de dados | Exemplo | Validador |
|---|:---:|---|---|:---:|:---:|---|---|
| nome | Sim | Nome User| user name | Body | String | "Usuario teste" | Máximo: 100 |
| email | Sim | Email User| user email | Body | String | "email@teste.com" | Máximo: 100 |
| password | Sim | Password user | Password user | Body | String | "12345678912345" | Máximo: 100 |


### **Exemplo Request:**
```
/api/user
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
| id | Código identificador da user | User identifier | Long | 1 | 
| nome | Nome User | user name | String | "Usuario teste" | 
| email | Email User | user email | String | "email@teste.com" | 
| password | Password user | Password user | String | "kajdfaiosdfjowweowejwie" |
 

**Código status da resposta HTTP: 201 - Created**

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

**Fluxo de execução**

1. Converter o objeto de persist na entidade user
2. Verificar existência de user já registrado com o email informado  
2.1. Se existir, retornar exceção com email já cadastrado
3. Salvar entidade
4. Converter a entidade no response
5. Retornar o response

## **Casos de teste**

| Onde | Teste | HTTP Status | Resultado |
| :---: | --- | :---: | --- |
| Service | Quando cadastra user | 201 | Ok |
| Service | Quando cadastra user com email já registrado | 400 | Bad Request |
| DTO | Quando a quantidade de caracteres do atributo nome for maior que 255 |  400 | Bad Request |
| DTO | Quando a quantidade de caracteres do atributo email for maior que 100 |  400 | Bad Request |
| DTO | Quando a quantidade de caracteres do atributo password for maior que 100 |  400 | Bad Request |
| DTO | Quando o atributo password não existir | 400 | Bad Request |
| DTO | Quando o atributo email não existir | 400 | Bad Request |
| DTO | Quando o atributo nome não existir | 400 | Bad Request |

## **Exceções**

| HTTP Status | Chave | Mensagem | Message (en-US) |
|:---:|---|---|---|
| 400 | EMAIL_JA_CADASTRADO | Email já cadastrado | Email already registered |