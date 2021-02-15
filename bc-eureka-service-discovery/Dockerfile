FROM openjdk:11.0.7-jdk

VOLUME /tmp

COPY build/libs/bc-eureka-service-discovery.jar bc-eureka-service-discovery.jar

ENTRYPOINT ["java","-jar","/bc-eureka-service-discovery.jar"]