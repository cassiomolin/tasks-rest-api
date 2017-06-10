# tasks

Example of REST API using:

* Spring Boot
* Jersey
* Jackson
* MapStruct
* Hibernate Validator

## How to build and run this application?

To build and run this application, follow these steps:

1. Open a command line window or terminal.
1. Navigate to the root directory of the project, where the `pom.xml` resides.
1. Compile the project: `mvn clean compile`.
1. Package the application: `mvn package`.
1. Change into the `target` directory: `cd target`
1. You should see a file with the following or a similar name: `tasks-1.0.jar`.
1. Execute the JAR: `java -jar tasks-1.0.jar`.
1. The application should be available at `http://localhost:8080/api`.

## What will you find in this application?

When the application starts up, the database will be populated with some rows. 

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

Filtering tasks by description and completed status:

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

### Update a task status

```bash
curl -X PUT \
  http://localhost:8080/api/tasks/5/status \
  -H 'Content-Type: application/json' \
  -d '{
  "completed": true
}'
```

### Delete a task

```bash
curl -X DELETE \
  http://localhost:8080/api/tasks/5
```

## Targeting the API with Postman

Alternatively to cURL, you can use [Postman][] to target the REST API. The Postman files are available in the [`postman`][postman GitHub directory] directory.

[Postman]: https://www.getpostman.com/
[postman GitHub directory]: https://github.com/cassiomolin/tasks/tree/master/postman