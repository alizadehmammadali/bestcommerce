FROM openjdk:11.0.7-jdk

VOLUME /tmp

COPY build/libs/bc-ms-notification.jar bc-ms-notification.jar

ENTRYPOINT ["java","-jar","/bc-ms-notification.jar"]