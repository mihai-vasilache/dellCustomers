# dellCustomers

#### Test application: web service for adding new customers
	
* App is deployed on: https://dell-customers.herokuapp.com/
* Swagger api documentation available at: https://dell-customers.herokuapp.com/swagger-ui.html

To run the application locally a postgres database is needed. The connection parameters are in: 
src/main/resources/application-dev.yml configuration file:
```
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres
```

To run the application:
```
$ ./gradle bootRun
```
or build:
```
$ ./gradle build
```
and run:
```
$ java -jar build/libs/customers-0.0.1-SNAPSHOT.jar
```
Note: The application that runs locally has 100 mock customers.

Note: An aditional endpoint GET: /api/v1/customers was provided besides the requested POST: /api/v1/customers for easy ui testing.

Local checks: 

* api doc https://localhost:8080/swagger-ui.html

* Add customer:
```
$ curl -X POST http://localhost:8080/api/v1/customers -H "Content-Type: application/json" -d '{"name": "John Doe","email": "mail@test.com"}'
```
* Query customers:
```
curl -X GET http://localhost:8080/api/v1/customers?name=John&email=com
```