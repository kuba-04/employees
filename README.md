# Employee management app

This simple app allows you to perform basic CRUD operations on Employee object.

## Getting Started

If you want to quickly get the server up and running, run these instructions:
```
./gradlew build
java -jar ./build/libs/employment-x.x.x-SNAPSHOT.jar
```
In place of `x.x.x` put the current application version.

By default the application is running on localhost port 8080. This can be configured in application.yml

Once the application is started you can make HTTP calls to the server.
You can use Swagger to see the docs:

```
http://localhost:8080/swagger-ui.html
```

## Running the tests

```
./gradlew check
```
