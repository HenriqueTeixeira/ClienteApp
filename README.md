# ClienteApp

ClienteApp é um microsserviço desenvolvido em **Spring Boot** para enviar notificações para o **RabbitMQ**. Ele publica mensagens estruturadas contendo um **identificador do sistema**, um **título** e um **corpo da mensagem**, que são consumidas pelo **NotificationHub**.

## 📌 **Tecnologias Utilizadas**

- **Java 17**
- **Spring Boot 3**
- **RabbitMQ** (mensageria)
- **Docker** (para conteinerização)
- **Maven** (gestão de dependências)
- **CloudAMQP** (opcional para RabbitMQ na nuvem)

---

## 🚀 **Configuração e Execução**

### **1️⃣ Configurar o RabbitMQ**

Caso utilize **RabbitMQ local**, certifique-se de que o servidor está rodando:

```bash
# Executando RabbitMQ localmente
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Acesse [**http://localhost:15672/**](http://localhost:15672/) (Usuário: `guest`, Senha: `guest`) para verificar se está rodando corretamente.

Caso utilize um **RabbitMQ na nuvem (CloudAMQP)**, ajuste a conexão no `application.yml`:

```yaml
spring:
  rabbitmq:
    addresses: amqps://<usuario>:<senha>@<host>.cloudamqp.com/<vhost>
```

---

### **2️⃣ Clonar o repositório e compilar**

```bash
git clone https://github.com/seuusuario/ClienteApp.git
cd ClienteApp
./mvnw clean package -DskipTests
```

---

### **3️⃣ Executar a aplicação**

#### 🔹 **Sem Docker** (localmente com Maven)

```bash
java -jar target/clienteapp-0.0.1-SNAPSHOT.jar
```

#### 🔹 **Com Docker**

```bash
docker build -t clienteapp .
docker run -d -p 8080:8080 --name clienteapp clienteapp
```

#### 🔹 **Com Docker Compose** (se houver um `docker-compose.yml` configurado)

```bash
docker-compose up --build -d
```

---

## 📬 **Testando o Envio de Mensagens**

Para enviar uma notificação ao **RabbitMQ**, execute:

```bash
curl -X POST "http://localhost:8080/publish" \
-H "Content-Type: application/json" \
-d '{
  "systemId": "Financeiro",
  "title": "Pagamento Recebido",
  "body": "Seu pagamento de R$ 1500 foi processado com sucesso."
}'
```

Se tudo estiver certo, a aplicação deve imprimir no log:

```
📤 Tentando enviar mensagem para RabbitMQ: { systemId: "Financeiro", title: "Pagamento Recebido", body: "Seu pagamento foi aprovado!" }
✅ Mensagem enviada com sucesso para a fila: notificacao_queue
```

---

## 🔍 **Verificando as Mensagens no RabbitMQ**

Acesse [**http://localhost:15672/**](http://localhost:15672/) e navegue até **Queues > notificacao\_queue** para verificar as mensagens na fila.

Ou via terminal:

```bash
docker exec -it rabbitmq rabbitmqctl list_queues
```

Se tudo estiver correto, a fila `notificacao_queue` deve conter mensagens.

---

## 🛠 **Estrutura do Projeto**

```
ClienteApp/
├── src/
│   ├── main/
│   │   ├── java/br/com/devops/clienteapp/
│   │   │   ├── ClienteAppApplication.java   # Classe principal
│   │   │   ├── config/RabbitMqConfig.java  # Configuração do RabbitMQ
│   │   │   ├── controller/PublisherController.java  # Endpoint para envio de mensagens
│   │   │   ├── service/MessagePublisher.java  # Serviço que publica mensagens
│   │   │   ├── dto/MessageDTO.java  # Modelo de mensagem
│   ├── resources/
│   │   ├── application.yml  # Configuração do Spring Boot
│
├── target/  # Arquivos compilados (após o build)
├── Dockerfile  # Configuração do container
├── docker-compose.yml  # Configuração Docker Compose (opcional)
├── README.md  # Documentação do projeto
└── pom.xml  # Dependências do Maven
```

---

## 🤝 **Contribuição**

Se quiser contribuir com melhorias no **ClienteApp**, siga estes passos:

1. **Fork** o repositório
2. Crie uma **branch** (`git checkout -b feature-minha-melhoria`)
3. Faça as alterações e **commite** (`git commit -m 'Minha melhoria'`)
4. **Push** na branch (`git push origin feature-minha-melhoria`)
5. Abra um **Pull Request**

---

## 📄 **Licença**

Este projeto é distribuído sob a licença **MIT**. Sinta-se à vontade para utilizá-lo e melhorá-lo!

---

## 📞 **Contato**

📧 **Email**: [hteixeira2007@gmail.com](mailto\:hteixeira2007@gmail.com)\
🔗 **GitHub**: [HenriqueTeixeira](https://github.com/HenriqueTeixeira)\
🚀 **LinkedIn**: [Henrique Teixeira](https://www.linkedin.com/in/henrique-teixeira-b0574598/)

---

