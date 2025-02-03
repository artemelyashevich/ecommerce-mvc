FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM tomcat:9.0.69-jdk17-temurin
WORKDIR /usr/local/tomcat
COPY --from=build /app/target/ecommerce-application-1.0-SNAPSHOT.war ./webapps/ecommerce-application-1.0-SNAPSHOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]