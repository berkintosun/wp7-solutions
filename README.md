# Cern Assignments

This document provides an overview of the tasks implemented for this project, detailing the design decisions, implementation steps, and assumptions made during development.

## General Guidelines

- The code is designed to be **readable** and **maintainable** for real-life scenarios.
- Proper naming conventions, documentation, and tests are provided.
- Assumptions are explicitly stated for any ambiguous instructions.
- The project is published on a Git repository for the review purposes. Access to the repository will be restricted whenever requested.
- In commits, mentioned the task number that the commit belongs to.
- I did not create PR for each task as I was worried of confusing the reviewer but in my opinion separate branch and PR approach would be cleaner. 
---
## Task Specific Descriptions

Each task has its own `README` file.

### [Task 1: Detecting Duplicate Elements in a List](duplicate-finder/)
### [Task 2: Spreadsheet Engine in Java](spreadsheet-engine/)
### [Task 3: Java Library for Dependency Resolution](dependency-resolver/)

---

## Docker Compose Support

To simplify the usage and execution of the project, a `docker-compose.yml` file is included. It allows you to build, test, and run all tasks within isolated containers and get the built jar in the sub-project folder.

### Running the Docker Compose File

To build and test all tasks, run:

```bash
docker-compose up
```
This will create clean build and run the tests & copy the jar to the task folder itself. Naming convention for the jar is `{project_name}-{version}.jar` (e.g. `spreadsheet-engine-1.0-SNAPSHOT.jar`)
### Accessing the Containers
Docker containers intentionally kept running after build process is done, in order to allow users to be able to run `docker compose exec`
if they would like to execute specific command through an environment that contains Java 21 & Gradle 8.1

Task-1 is also support application plugin of gradle and you can run using `gradle run --args "b a c c e a c d c d"`
or `docker compose exec duplicate-finder gradle run --args "b a c c e a c d c d"`

Build and test using Docker Compose as described above, or manually using Gradle (or Gradle Wrapper):

```bash
gradle clean build test
```
