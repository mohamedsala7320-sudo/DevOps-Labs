# Lab 2: Building and Packaging Go Application with Gradle & Docker

This lab demonstrates how to build, package, test, and run a Java Calculator application using **Gradle** and **Docker** (Single-stage build approach).

---

## 📌 Objectives
- Install and use Gradle.
- Clone the source code of the Gradle Calculator application (`https://github.com/Ibrahim-Adel15/CalculatorGradleApp.git`).
- Run unit tests using Gradle (`gradle test`).
- Build and package the application to generate the artifact at `build/libs/calculator.jar` (`gradle build`).
- Run and verify the application is working successfully.

---

## 🛠️ Prerequisites
- **OS**: Ubuntu (WSL) / Linux
- **Tools Installed**:
  - Docker Engine
  - Git

---

## 🚀 Step-by-Step Execution

### 1. Project Setup
First, clone the source code into the Lab 2 directory[cite: 16]:
```bash
git clone [https://github.com/Ibrahim-Adel15/CalculatorGradleApp.git](https://github.com/Ibrahim-Adel15/CalculatorGradleApp.git) .
```

### 2.Docker Configuration
A single-stage Dockerfile was created using the official Gradle image to handle testing, building, and runtime.
```bash
FROM gradle:8.5-jdk17-alpine

WORKDIR /app

COPY . .

RUN gradle test

RUN gradle build

CMD ["java", "-jar", "build/libs/calculator.jar"]
```

### 3. Build the Docker Image
To compile, run unit tests, and package the application inside the Docker image, run:
```bash
docker build -t calculator-gradle-lab2 .
```

### 4. Run the Application
Since the calculator application requires CLI user input, run the container in interactive mode (-it):
```bash
docker run --rm -it calculator-gradle-lab2
```

---

## 📸 Screenshots & Verification

All execution steps, unit test results, build logs (BUILD SUCCESSFUL), and app verification outputs have been captured and saved in the screenshots/ directory:

1.Source Code Cloning

2.Gradle Unit Tests & Build Logs

3.Interactive Container Run & Verified Output

---

## 📂 Directory Structure

```bash
Lab2/
├── Dockerfile
├── README.md
├── build.gradle
├── src/
└── screenshots/
    ├── step1-clone.png
    ├── step2-build.png
    └── step3-run.png
```
---
