# helidon-jpa-ucp
Proof of concept for Helidon Microprofile support for JPA and Oracle Universal Connection Pool (UCP) with OracleDB

# To get it running
Prepare your Oracle database with a suitable table and record, using this sql statements:
```bash
CREATE TABLE GREETING (
    SALUTATION VARCHAR(64),
    RESPONSE VARCHAR(64),
    constraint pk_salutation primary key(salutation)
);
INSERT INTO GREETING (SALUTATION, RESPONSE) VALUES ('Marco', 'Polo');
```
Clone the project on your workstation:
```bash
git clone https://github.com/doschkinow/helidon-jpa-ucp.git
```
In the project directory edit src/main/resources/application.yaml to replace the URL and password properties with the values for your Oracle database.

Build the project (with jdk-11 and maven-3.6.3):
```bash
cd helidon-jpa-ucp
mvn package
```

Run the application:
```bash
java -jar target/helidon-jpa-ucp.jar
```
In another terminal or in a browser visit http://localhost:8080/example/response/Marco
```bash
curl http://localhost:8080/example/response/Marco
```
The response should be "Polo". Observe the console log in the terminal where the application is running.

# Credits
This project is an adaptation of https://medium.com/helidon/helidon-and-jpa-da20492f5395 where the Hikari connection pool and the H2 database were exchanged with Oracle UCP and Oracle database