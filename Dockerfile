# Usar a imagem oficial do OpenJDK 17
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o JAR gerado para o contêiner
COPY target/ClienteApp-0.0.1-SNAPSHOT.jar ClienteApp.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "ClienteApp.jar"]
