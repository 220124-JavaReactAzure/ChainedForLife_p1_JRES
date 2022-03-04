# ChainedForLife_p1_JRES - Project 1 - Wedding Planner
## Project Description
The Wedding Planner app was developed for a small wedding planning company that is looking to help streamline the management and allocation of wedding resources to the betrothed. They can access various assets to be assigned to their wedding, checking asset availability dates and allowing attendees to join with their contact information, meal choices and plus one information. This RESTful application leverages a local tomcat server to handle incoming requests to thoroughly tested services that must be persisted using Hibernate and Azure SQL services.
## Technologies Used
- Java
- Mockito
- JUnit
- Git
- Maven
- Log4J
- Azure SQL Database
- Tomcat
- -REST
- Hibernate
## Developers 
- Joshua Robles
- Andy Escobar
## Goals
- Develop a faux wedding web app for P1
- Make a REST api for requests to be made
- Use a webpage to access and use the database
## Program Flow
- Servlet waits for requests and respondes
- Responses are handled by the service layer which later calls the DAO layer (Service layer does some checking to make sure data is valid).
- DAO layer connects to the DB and grabs or inserts data.
## ER Diagram
![unknown](https://user-images.githubusercontent.com/32827900/155761291-d5f1105d-bfe3-497d-8a63-f7d309702e15.png)


