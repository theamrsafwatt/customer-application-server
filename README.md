# customer-application-server

This project was generated with Spring initializr (https://start.spring.io/)

## Development server

Run the following `mvn` command:
```bash
mvn spring-boot:run
`````
to start the server. The application will start listening on `http://localhost:9080/`. 
The app will automatically reload if you change any of the source files.

## Build

Run the following `mvn` command:
```bash
mvn clean package
`````
to build the project. The build artifacts will be stored in the `/target` directory. To Start up the java application run the following command: 
```bash
java -jar target/customer-application-server-1.0.0.jar
````` 
The application will start listening on `http://localhost:9080/`

## Docker
 After executing Build, run 
 ```bash
docker build --tag=customer-application-server:latest .
`````
To build from the image, then run
```bash
docker run -p9080:9080 customer-application-server:latest
`````
to start the server. This will start our application in Docker and we can access it from the host machine at localhost:9080/customer.

## Running unit tests

Run `mvn test` to execute the unit tests
