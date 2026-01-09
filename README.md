# appointment-demo-backend

## This is a simple project only for Login and Simple Appointment

I use the following components:
- Spring Boot Web as a simple web server
- Caffeine as the progress based cache 
- MyBatis as the DB dialect generator
- MyBatis Flex as the enhancement tool of MyBatis

Current project compiled and run on **JDK 21**

server runs on the port 8888 by default. 
DB schema name is TaroDemo

## How to RUN

1. clone the project and cd to TaroDemo
2. run command `gradle clean build` 
3. the jar package named `appointment-service.jar` generated under the path `build/libs`
4. run the command `java -jar appointment-service.jar`. The service will be started on port 8888.
