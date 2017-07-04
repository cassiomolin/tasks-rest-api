# Sample REST API for managing tasks using Spring Boot and Jersey

[![Build Status](https://travis-ci.org/cassiomolin/tasks-springboot-jersey.svg?branch=master)](https://travis-ci.org/cassiomolin/tasks-springboot-jersey)
[![MIT Licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/cassiomolin/tasks-springboot-jersey/master/LICENSE.txt)

Example of REST API using:

- **Spring Boot:** Framework for creating standalone Java applications.
- **Jersey:** JAX-RS reference implementation for creating RESTful web services in Java.
- **Jackson:** JSON parser for Java.
- **MapStruct:** Mapping framework for Java.
- **Hibernate Validator:** Bean Validation implemetation to define and validate application constraints.
- **REST Assured:** Testing framework for REST APIs.

## Building and running this application

To build and run this application, follow these steps:

1. Open a command line window or terminal.
1. Navigate to the root directory of the project, where the `pom.xml` resides.
1. Compile the project: `mvn clean compile`.
1. Package the application: `mvn package`.
1. Change into the `target` directory: `cd target`
1. You should see a file with the following or a similar name: `tasks-springboot-jersey-1.0.jar`.
1. Execute the JAR: `java -jar tasks-springboot-jersey-1.0.jar`.
1. The application should be available at `http://localhost:8080/api`.

When the application starts up, the database will be populated with some rows. 

## REST API overview

The application provides a REST API that currently supports the following operations to manage tasks:

### Create a task

```bash
curl -X POST \
  http://localhost:8080/api/tasks \
  -H 'Content-Type: application/json' \
  -d '{
  "description": "Pay internet bill"
}'
```
### Get all tasks

```bash
curl -X GET \
  http://localhost:8080/api/tasks \
  -H 'Accept: application/json'
```

This endpoint supports the following query parameters:

- `description` (string): Filter tasks by description (case-insensitive).
- `completed` (boolean): Filter tasks by completed status.

Filtering tasks by description:

```bash
curl -X GET -G \
  'http://localhost:8080/api/tasks' \
  -H 'Accept: application/json' \
  -d 'description=avocado'
```

Filtering tasks by completed status:

```bash
curl -X GET -G \
  'http://localhost:8080/api/tasks' \
  -H 'Accept: application/json' \
  -d 'completed=true'
```

Filtering tasks by description and by completed status:

```bash
curl -X GET -G \
  'http://localhost:8080/api/tasks' \
  -H 'Accept: application/json' \
  -d 'description=karate' \
  -d 'completed=true'
```

### Get a task by id

```bash
curl -X GET \
  http://localhost:8080/api/tasks/5 \
  -H 'Accept: application/json'
```

### Update a task

```bash
curl -X PUT \
  http://localhost:8080/api/tasks/5 \
  -H 'Content-Type: application/json' \
  -d '{
  "description": "Pay electricity bill",
  "completed": false
}'
```

### Update a task completed status

```bash
curl -X PUT \
  http://localhost:8080/api/tasks/5/completed \
  -H 'Content-Type: application/json' \
  -d '{
  "value": true
}'
```

### Delete a task

```bash
curl -X DELETE \
  http://localhost:8080/api/tasks/5
```

## Targeting the API with Postman

Alternatively to cURL, you can use [Postman][] to target the REST API. The Postman files are available in the [`postman`][postman GitHub directory] directory.

## Client application

A client application written in Angular 4.x is package with the main application. Browse to `http://localhost:8080`:

<img src="src/main/doc/frontend.png" width="600">

For the client application source code, refer to the [`tasks-frontend-angular`][frontend project] project.

[Postman]: https://www.getpostman.com/
[postman GitHub directory]: https://github.com/cassiomolin/tasks-springboot-jersey/tree/master/src/main/postman
[frontend project]: https://github.com/cassiomolin/tasks-frontend-angular