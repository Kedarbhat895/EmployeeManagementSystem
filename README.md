Employee Management System

This is an Employee Management System that is built using Spring Boot and MongoDB in Java. The system includes all REST API calls for managing employee data, along with exception handling and JUnit test cases.
Getting Started

To get started with the Employee Management System, you will need to have Java, Maven, and MongoDB installed on your system. You can then clone this repository and run the application using the following command:

mvn spring-boot:run

The application will start up on port 8080. You can then access the REST API using your preferred HTTP client.
API Calls

The following REST API calls are available:
Method	  Endpoint	              Description
GET	      /employees	            Get all employees
GET       /find                   Get employee with particular name and designation (AND operator on Mongo)
DELETE	  /delete/{id}	          Get an employee by ID
POST	    /createemployee	        Add a new employee
PUT	      /employees/{id}	        Update an employee by ID
GET       /salary	get total       salary in a particular designation (Group By clause on Mongo


Exception Handling

The Employee Management System includes exception handling for the following scenarios:

    Employee not found
    Invalid request
    Missing required fields
    MongoDB is not responding

Unit Tests

The Employee Management System includes JUnit tests for the following scenarios:

    Get all employees
    Get an employee by ID
    Add a new employee
    Update an employee by ID
    Delete an employee by ID
    Get employee with Constraints

Database
The Employee Management System uses MongoDB as its database. You can configure the database connection settings in the application.properties file.
Here the implementation is done using MongoTemplate as it supports more complex queries


Technologies Used
The following technologies were used in the development of the Employee Management System:

    Java 8
    Spring Boot
    MongoDB
    Maven
    JUnit
    Mockito


Contributing
Contributions to the Employee Management System are welcome. To contribute, please fork this repository, make your changes, and submit a pull request.
License

This project was inspired by the need for a simple and efficient employee management system.
Special thanks to the Spring Boot, MongoDB, and JUnit communities for their contributions to open-source software.
