FROM openjdk:11.0.7-jdk

VOLUME /tmp

COPY build/libs/bc-ms-api-gateway.jar bc-ms-api-gateway.jar

ENTRYPOINT ["java","-jar","/bc-ms-api-gateway.jar"]