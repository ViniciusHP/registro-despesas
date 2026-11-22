FROM maven:3.10.0-eclipse-temurin-25-resolute as builder

WORKDIR /app-builder

COPY . .

RUN mvn clean package

FROM eclipse-temurin:25.0.5.1_2-jre-ubi10-minimal

WORKDIR /app

ENV DATASOURCE_URL=jdbc:mysql://db/despesas
ENV DATASOURCE_USERNAME=mysql
ENV DATASOURCE_PASSWORD=mysecretpassword
ENV OPEN_AI_BASE_URL=http://localhost:11434/v1

COPY --from=builder /app-builder/target/registro-despesa*.jar /app/registro-despesas.jar

ENTRYPOINT ["java", \
    "-Dspring.datasource.url=${DATASOURCE_URL}", \
    "-Dspring.datasource.username=${DATASOURCE_USERNAME}", \
    "-Dspring.datasource.password=${DATASOURCE_PASSWORD}", \
    "-Dspring.ai.openai.base-url=${OPEN_AI_BASE_URL}", \
    "-jar", \
    "./registro-despesas.jar"]