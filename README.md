## Documentation

### Application consist of 4 main parts:

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
  - Produces and Consumes notification and sends email to merchant users 
  
#### RUN  
Before the run the application we should export variables </br>
Finding host ip: [Link](https://linuxize.com/post/how-to-set-and-list-environment-variables-in-linux/)
```shell
./gradlew build

export HOST_IP='YOUR HOST IP' 

export DB_USERNAME=yourdbuser

export DB_NAME=yourdbname

export DB_PASSWORD=yourdbpassword

docker-compose up -d
```

