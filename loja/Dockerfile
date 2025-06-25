# Etapa de build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia os arquivos de configuração e dependências
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o JAR (sem rodar os testes)
RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado do build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar o app
CMD ["java", "-jar", "app.jar"]
