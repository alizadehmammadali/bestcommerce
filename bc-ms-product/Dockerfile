FROM openjdk:11.0.7-jdk

VOLUME /tmp

COPY build/libs/bc-ms-product.jar bc-ms-product.jar

ENTRYPOINT ["java","-jar","/bc-ms-product.jar"]