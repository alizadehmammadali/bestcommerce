FROM openjdk:11.0.7-jdk

VOLUME /tmp

COPY build/libs/bc-ms-user.jar bc-ms-user.jar

ENTRYPOINT ["java","-jar","/bc-ms-user.jar"]