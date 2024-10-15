## Employee Manager API
Simple RESTful API for managing employee data, built using Spring Boot. The application allows CRUD (Create, Read, Update, Delete) operations on employee records, with features such as searching for employees by name or email, and automatic employee code generation.

### Features

- **Add a new employee**: Allows adding a new employee with a unique employee code generated automatically.
- **Get all employees**: Retrieve a list of all employees in the system.
- **Get employee by ID**: Fetch a specific employee's details by their unique ID.
- **Find employees by name**: Search for employees by name (supports partial name searches).
- **Update employee information**: Modify existing employee data.
- **Delete an employee**: Remove an employee from the system by ID.

### Technologies Used

Java, Spring Boot, Spring Data JPA, MySQL, Swagger/OpenAPI, Lombok, Maven.

### Packages and Classes

- **Model**: The Employee entity represents the core data model, with attributes like id, name, email, jobTitle, phone, imageUrl, and employeeCode.
- **Repository**: The EmployeeRepo interface extends JpaRepository to handle database operations. Custom queries are defined using Spring Data JPA’s query methods and @Query annotation.
- **Service**: The EmployeeService class contains business logic for employee operations.
- **Controller**: The EmployeeController handles API requests, mapping endpoints to service methods and using OpenAPI annotations for documentation.
- **Exception Handling**: A global exception handler captures errors like "User Not Found" and returns meaningful responses to the client.
- **Swagger Configuration**: The SwaggerConfig class provides OpenAPI documentation for easy understanding of the API.
- **CORS Configuration**: The application includes a CorsFilter bean in the main class to handle cross-origin requests, allowing connections from http://localhost:3000.

The front-end code for this application can be found in a separate repository: https://github.com/antoniomrg/Employee_Manager_FE.git
