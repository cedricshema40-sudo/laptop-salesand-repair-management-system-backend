\# Laptop Sales and Repair Management System



\## Student Information



\- \*\*Student Name:\*\* SHEMA CEDRICK

\- \*\*Student ID:\*\* 27639

\- \*\*Course:\*\* INSY 8322 – Web Technology and Internet

\- \*\*Institution:\*\* Adventist University of Central Africa (AUCA)



\## Project Description



The Laptop Sales and Repair Management System is a Spring Boot REST API developed to manage customers, technicians, workbenches, repair skills, laptop repair jobs, and part replacements.



The system uses PostgreSQL for database management.



\## Technologies Used



\- Java

\- Spring Boot

\- Spring Data JPA

\- PostgreSQL

\- Maven

\- Postman



\## Database Configuration



1\. Install and start PostgreSQL.

2\. Create a database named:



&#x20;  `laptop\_salesand\_repair\_management\_system`



3\. Configure your database username, password, and connection URL in `src/main/resources/application.properties`.



Example configuration:



```properties

spring.datasource.url=jdbc:postgresql://localhost:5432/laptop\_salesand\_repair\_management\_system

spring.datasource.username=postgres

spring.datasource.password=postgres



spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true



server.port=8080

```



Replace the username and password with your local PostgreSQL credentials.



\## How to Run the Project



1\. Clone the repository:



```bash

git clone https://github.com/cedricshema40-sudo/laptop-salesand-repair-management-system-backend.git

```



2\. Open the project directory:



```bash

cd laptop-salesand-repair-management-system-backend

```



3\. Configure PostgreSQL as described above.



4\. Run the application on Windows:



```powershell

.\\mvnw.cmd spring-boot:run

```



5\. The API will be available at:



`http://localhost:8080`



\## Testing the API



Import the Postman collection located in the project root:



`laptop-salesand-repair-management-system-midterm.postman\_collection.json`



Use Postman to test the available API endpoints.



\## GitHub Repository



https://github.com/cedricshema40-sudo/laptop-salesand-repair-management-system-backend

