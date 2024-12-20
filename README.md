# API Spring Project

This project is a simple API developed using Spring Boot, designed for educational purposes. The API connects to a MySQL database to perform CRUD (Create, Read, Update, Delete) operations on a `Person` entity.

## Features

- **CRUD Operations**: Create, retrieve, update, and delete `Person` records in the database.
- **Validation**: Ensures that the `Person` entity has valid data before performing operations.
- **Custom Queries**: Includes custom repository methods for specific data retrieval needs.

## Prerequisites

- **Java 17**: Ensure that Java 17 is installed on your machine.
- **Maven**: Required for building and managing the project dependencies.
- **MySQL Database**: Set up a MySQL database to connect with the application.

## Getting Started

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/buenolas/api-spring.git
   ```

2. **Configure the Database**:

   Update the `application.properties` file located in `src/main/resources/` with your MySQL database credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build the Project**:

   Navigate to the project directory and run:

   ```bash
   mvn clean install
   ```

4. **Run the Application**:

   Start the Spring Boot application using:

   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**:

   The API will be accessible at `http://localhost:8080`.

## API Endpoints

### CRUD Operations

- **Create a Person**
  - **POST** `/api`
  - **Request Body**:
    ```json
    {
      "name": "John Doe",
      "age": 30
    }
    ```

- **Get All Persons**
  - **GET** `/api`

- **Get Person by ID**
  - **GET** `/api/{id}`

- **Update Person**
  - **PUT** `/api/{id}`
  - **Request Body**:
    ```json
    {
      "name": "Jane Doe",
      "age": 25
    }
    ```

- **Delete Person**
  - **DELETE** `/api/{id}`

### Additional Endpoints

- **Count Total Persons**
  - **GET** `/api/count`

- **Order Persons by Name**
  - **GET** `/api/orderName`

- **Order Persons by Age**
  - **GET** `/api/orderAge`

- **Order Persons by Name and Age**
  - **GET** `/api/orderNameAge`

- **Search Persons Containing Name**
  - **GET** `/search/containing/{term}`
  - **Path Variable**: `term` (the term to search for)

- **Sum of All Ages**
  - **GET** `/api/sumAges`

- **Search Persons by Age or Higher**
  - **GET** `/search/ages/{age}`
  - **Path Variable**: `age` (the minimum age to search for)

## Custom Repository Methods

The `Repository` interface extends `CrudRepository` and includes custom methods:

- `List<Person> findByOrderByName();`
- `List<Person> findByOrderByAge();`
- `List<Person> findByNameOrderByAgeDesc(String name);`
- `List<Person> findByNameContaining(String word);`
- `@Query("SELECT SUM(age) FROM persons") int sumAges();`
- `@Query("SELECT * FROM persons WHERE age >= :age") List<Person> searchAgeOrHigher(int age);`

## Error Handling

The service layer includes validation to handle errors gracefully, returning appropriate HTTP status codes and messages for invalid inputs or operations.

## License

This project is licensed under the MIT License.

## Acknowledgments

This project is inspired by the need to understand Spring Boot and its integration with MySQL for building RESTful APIs.

For more information, visit the [Spring Boot Documentation](https://spring.io/projects/spring-boot). 
