FROM maven:latest AS build
COPY . .
RUN mvn clean package -e -Pprod -DskipTests

FROM openjdk:17

COPY --from=build /target/rembg-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","demo.jar"]
