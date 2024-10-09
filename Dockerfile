FROM openjdk:17
WORKDIR /app
COPY ./target/spring-boot-swagger-aws.jar /app
EXPOSE 8080
CMD ["java", "-jar", "spring-boot-swagger-aws.jar"]
