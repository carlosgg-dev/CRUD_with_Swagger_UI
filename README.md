# Basic CRUD with Swagger UI

## Introducción
Basic example of CRUD maintenance of Elements, an object that can represent anything.  

The controller sends the requests to the service layer, which handles the business logic.  
The service is responsible for obtaining the necessary data sent by the repository, and after processing it,  
it sends it back to the controller.

The application has an in-memory database H2 with five records saved, configured in the data.sql file;

## Requirements
Before being able to develop and/or deploy the application locally, it is necessary to have installed:

- Java JDK 17 - https://www.oracle.com/java/technologies/downloads/

## Import project
Clone in your IDE the project from the GitHub repository - https://github.com/carlosgg-dev/CRUD_with_Swagger_UI.git

## Compilation
Once the project has been imported, verify that it is configured for JDK 17:
- Settings/Build, Execution, Deployment/Compiler/Java Compiler
- File/Project Structure/Modules/*

Build the application with the following Maven command from the root of the project.  
It can also be run from the Maven console itself, located in the Maven tab.
```
mvn clean install
```

## Run configuration
Before starting the application it is necessary to create a startup configuration in the Run/Edit configurations tab.  
In the options window configure the JDK used and select the main class of the application:
- Select JDK 17
- In Main class: org.example.Application

## Run the app
When starting the application Spring adds a Tomcat server to the build listening on port :8080.  
In the path http://localhost:8080/crud/swagger-ui/index.html in the browser the deployed application will open.  
With Swagger you can make requests to the controllers through a graphical interface
