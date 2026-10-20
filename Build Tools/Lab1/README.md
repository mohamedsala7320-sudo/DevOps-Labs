# Lab 1: Building and Packaging Java Application with Maven & Docker

This lab demonstrates how to build, package, and run a Java-based Calculator application using **Apache Maven** and **Docker**.

---

## 📌 Objectives
- Clone the source code of the Java Calculator application.
- Build and test the application using Maven.
- Package the application into an executable `.jar` file (`target/calculator.jar`).
- Containerize the application using Docker for consistent execution environments.

---

## 🛠️ Prerequisites
- **OS**: Ubuntu (WSL) / Linux
- **Tools Installed**:
  - Docker Engine
  - Git

---

## 🚀 Step-by-Step Execution

### 1. Project Setup
First, clone the source code into the lab directory:
```bash
git clone [https://github.com/Ibrahim-Adel15/CalculatorMavenApp.git](https://github.com/Ibrahim-Adel15/CalculatorMavenApp.git) .
```

## 2. Dockerfile Configuration
A Dockerfile was created to handle the environment setup, building, and running the application

```bash
FROM maven:3.10-eclipse-temurin-17-alpine

WORKDIR /app

COPY . .

RUN mvn package

CMD ["java", "-jar", "target/calculator.jar"]

EXPOSE 8080
```


## 3.Build the Docker Image
To compile the Java application and package it inside a Docker image, run:

```bash
docker build -t calculator-lab1 .
```

## Run the APP
Since the application requires user input (CLI interaction), run the container in interactive mode (-it):

```bash
docker run --rm -it calculator-lab1
```
---

📸 Screenshots & Verification
All execution steps, build logs, and test results have been captured and saved in the screenshots/ directory:
Installation / Setup verification
Docker Build Log (mvn package execution)
Interactive Container Run Output

---

📂 Directory Structure

```bash
Lab1/
├── Dockerfile
├── README.md
├── pom.xml
├── src/
└── screenshots/
    ├── Installation.png
    └── ...
```

