### build and ci/cd information
[![Java CI with Maven](https://github.com/CGS-IT/jee_microservices_training_2025/actions/workflows/maven.yml/badge.svg)](https://github.com/CGS-IT/jee_microservices_training_2025/actions/workflows/maven.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/jee_micro/jee_micro/badge.svg)](https://maven-badges.herokuapp.com/maven-central/jee_micro/jee_micro)
[![codecov](https://codecov.io/gh/CGS-IT/jee_microservices_training_2025/branch/main/graph/badge.svg?token=PBF8CW2H9X)](https://codecov.io/gh/CGS-IT/jee_microservices_training_2025)
[![License: CC BY-NC 4.0](https://img.shields.io/badge/License-CC%20BY--NC%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-nc/4.0/)

> Release Information: [![Release Info](https://img.shields.io/github/release/CGS-IT/jee_microservices_training_2025.svg)](https://github.com/CGS-IT/jee_microservices_training_2025/releases)

# Workshop : JEE Microservice Development with Quarkus

This project is used as an example project for jee microservice development with quarkus 
and is part of the course material for the course "Microservice Development with Quarkus" 
see ./license.txt for license information.

see Project for Slides https://github.com/CGS-IT/jee_microservices_training_materials_2025

<p align="center">
  <img src="./images/training.png" alt="Quarkus Microservice Workshop" width="300"/>
</p>

This project uses Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

This work © 2025 by CGS-IT Solutions GmbH is licensed under Attribution-NonCommercial 4.0 International

# Related Dokumenentation and Guides
## Rest Easy 

- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and
  more

### Provided Code

### RESTEasy JAX-RS

## JPA Quarkus 
- Quarkus Hibernate JPA [https://quarkus.io/guides/hibernate-orm]

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

# Running the application
# Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/kurs_jeemicro-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.
