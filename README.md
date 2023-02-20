# ms-account-service

Assumption: - Machine with java, gradle, intellij idea.
1. Import the project from github.
2. Run the docker compose to install containers for postgres and pgAdmin.
3. Run maven clean install to generate the mapstruct java files.
4. Enable annotation processing in intellij idea (for lombock).
5. Run the Application main class from intellij idea.

Technologies used: - Java, spring-boot, mapstruct(for mapping), maven, postgres, h2-DB(for testing) liquibase.

pgAdmin
--------
http://localhost:15433/browser/

Swagger
--------
http://localhost:8082/swagger-ui/index.html

Sample rest endpoint calls
----------------------------
1. List accounts by user profile id: http://localhost:8082/api/accounts/1
2. List account transactions by account number: http://localhost:8082/api/accountTransactions/1

Configured matching user profile id in postgres DB are 1, 2 and 3.

Configured matching account number in postgres DB are 1 and 6.

