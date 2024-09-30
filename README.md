# Spring Football OLAP

This project is a Spring application that consumes data from an external API for the 2023 Brazilian Serie A Football Championship. It organizes the data into an OLAP cube and provides analytical queries through endpoints.

## Features

- **Data Consumption**: Fetches data from an external football API.
- **OLAP Cube**: Structures data in an OLAP cube for multidimensional analysis.
- **Analytical Queries**: Exposes endpoints for performing analytical queries on the data.

## Technologies Used

- **Spring Boot**: The main framework for building the application.
- **Java**: The programming language used for development.
- **REST API**: For consuming the external football data.
- **OLAP**: For data analysis and organization.

## Getting Started

To run this project locally, follow these steps:

### Prerequisites

- Java 17 or higher
- Maven
- Git

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/acbueno/spring-football-olap.git
   ```

2. Navigate to the project directory:
   ```bash
   cd spring-football-olap
   ```
3. Build the project using Maven:
 ```bash
mvn clean install
 ```
4. Run the application:
 ```bash
   mvn spring-boot:run
 ```

### Accessing the API
Once the application is running, you can access the API endpoints for analytical queries. Refer to the API documentation for more details on the available endpoints.

### Usage
This application can be used to analyze football data, including:

- Team statistics
- Player performance

