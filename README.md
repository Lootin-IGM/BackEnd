<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Developed with</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#requirements">Requirements</a></li>
        <li><a href="#clone-the-repository">Clone the Repository</a></li>
        <li><a href="#create-mysql-database">Create MySQL Database</a></li>
        <li><a href="#running-app">Running the application locally</a></li>
      </ul>
    </li>
    <li>
      <a href="#developers">Developers</a>
    </li>
  </ol>
</details>


# Lootin - BackEnd Application

## About The Project
Here is the Lootin's BackEnd application which follows a REST architecture.
It contains all the "reflection" behind the Front application and ensures the connection to the SQL database.
It's recommended to use this application with the "Front" repository of Lootin-IGM organization.

### Developed with
* [Java](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)
* [Spring-Boot](https://spring.io/projects/spring-boot)
* [MySQL](https://www.mysql.com/fr/)

## Getting Started
### Requirements
For building and running the application you need:
- [JDK 1.11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

### Clone the Repository
As usual, you get started by cloning the project to your local machine:
```
git clone https://github.com/Lootin-IGM/BackEnd.git
```

### Create MySQL Database
To use Lootin, you must create a database named "lootin" and create a user with all the rights on it having for login: "lootin" and for password "lootin".

### Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `src/main/java/fr/uge/lootin/back/LootinApplication` class from your IDE.

## Developers
This project was developed by:
* [BESNARD Pierre-Jean](https://github.com/PJbesnard)
* [BILLAUT Louis](https://github.com/LouisBillaut)
* [CALONNE Thomas](https://github.com/calonnet)
* [CROHARE Jeanne](https://github.com/jcrohare)
* [JUILLARD Thomas](https://github.com/JUILLARD-Thomas)
* [LIEGEY Armand](https://github.com/afkeu)
