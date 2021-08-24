## Fibonacci app

This project contains a Spring Boot application. It that has a single endpoint that calculates the Nth Fibonacci number.

### Sample request/response
Request:

`curl http://localhost:8000/fib?n=10`

Response:
```
{
    "result": 55
}
```

### Tech stack
* Java 16
* Spring Boot
* Maven
* JUnit 5
* RestAssured

### Building
The project includes a Dockerfile to build an image from the project. 
To create the image, run `docker build . -t fibonacci`.

### Deploying
A Kubernetes YAML configuration file has been created to deploy the Docker image. 
To deploy it, run `kubectl apply -f fib.yaml`.

#### Scaling up/down
By default, 1 container is started when the application is deployed.
To support a change of load in the application, the number of containers can be increased or decreased.

This can be achieved by running `kubectl scale -f fib.yaml --replicas <num of replicas>`.

For example, to increase the number of containers to 10, you should run `kubectl scale -f fib.yaml --replicas 10`.  

### Testing
The project includes API tests. They start the server and issue a 
series of API calls, ensuring the results match the expectations.

To execute the tests, run `mvn test`.