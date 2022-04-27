# charm-api
## Project Description

An item catalog containing magical trinkets for sale. The API contains endpoints for creating, finding, editing, and deleting charms, users, and locations. Users can register for accounts with different access levels. Login is implemented using JWT tokens. Application is migration of a containerized RESTful Javalin web application to Spring Boot deployed using Docker-Compose. Application logs are exported using Promtail to Loki and monitored through a Grafana dashboard.

## Technologies Used

   - Maven
   - Docker
   - Git
   - Log4J
   - Spring Boot
   - Spring Data
   - Spring Security
   - Loki
   - Grafana
   - PostgreSQL
   - JWT

## Features

All basic CRUD operations are implemented for each of the data models. Access to non-safe http methods are restricted to authorized users.

  - New User accounts can be created.
  - Unauthenticated Users can access the GET methods for the Charm and Location endpoints.
  - Authenticated Users with the Role SELLER can access all methods for the Charm and Location endpoints, as well as the GET method for the Users endpoint.
  - Authenticated Users with the Role ADMIN can access all methods for all endpoints.

To-do list:

   - Add getCharmsBySeller() method at users/{id}/charms
   - Add getCharmsByLocation() method at locations/{id}/charms
   - Add support for query params for the user and location tables
   - Add user accounts and purchasing functionality
   - Users can view a list of owned items

## Getting Started

  Install Docker and git on your computer.
  ```
  git clone https://github.com/jazlyn-maxwell/charm-api.git
  ```
  After cloning the repository, run the following commands in the main directory:
  ```
  docker build .
  dcoker-compose up
  ```
  The server should be up and running.

## Usage

    API requests can be made at localhost:8081
    
    requests can be made at the follwoing endpoints:
    
    - GET, POST /charms
    - GET, PUT, DELETE /charms/{id}
    - GET, POST /users
    - GET, PUT, DELETE /users/{id}
    - GET, POST /locations
    - GET, PUT, DELETE /locations/{id}
    - POST /auth
