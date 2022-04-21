FROM maven:3.6.3-openjdk-8 as builder
COPY src/ src/
COPY pom.xml pom.xml
RUN mvn package -Dmaven.test.skip

FROM java:8 as runner
COPY --from=builder target/charmShop.jar charmShop.jar
ENTRYPOINT ["java", "-jar", "/charmShop.jar"]