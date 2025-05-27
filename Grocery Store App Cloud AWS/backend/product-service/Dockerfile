# Do 'docker pull openjdk:17-jdk-slim' if not found
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ARG DB=database/product.db
COPY ${DB} backend/product-service/database/product.db
ENTRYPOINT ["java","-jar","/app.jar"]