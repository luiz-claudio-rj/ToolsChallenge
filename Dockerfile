# Utilizar uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo de build (pom.xml e src) para o diretório de trabalho
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn

# Baixar as dependências e compilar o projeto
RUN ./mvnw package -DskipTests

# Expor a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "target/desafio-0.0.1-SNAPSHOT.jar"]
