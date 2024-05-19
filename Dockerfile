FROM eclipse-temurin:17-jdk-jammy

# Copie o restante dos arquivos
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
