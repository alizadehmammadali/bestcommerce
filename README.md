## Documentation

### Application consist of 5 main parts:

* bc-eureka-service-discovery : Service registry 
* bc-ms-api-gateway <br/>
  - Api gateway
  - Zuul proxy server 
  - JWT authentication

* bc-ms-user (Merchant)
  - User registration
  - Verification -> When user register it sends verification email via <b>bc-ms-notification</b> service. If user accept and clicks the link in the email, verification completes successfully.
  - Get logged user info details
* bc-ms-product
  - Create product
  - List api with pagination

* bc-ms-notification
  - Produces and Consumes notification and sends email to merchant users with Kafka MQ
  
#### RUN  
Before the run the application docker-compose we should export variables which are used in containers </br>
```shell
./gradlew build

export HOST_IP=$(ip route get 8.8.8.8 | head -1 | awk '{print $7}') 

export DB_USERNAME=yourdbuser

export DB_NAME=yourdbname

export DB_PASSWORD=yourdbpassword

docker-compose up -d
```

