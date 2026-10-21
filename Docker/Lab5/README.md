# Lab 5: Docker Image Vulnerability Scanning with Trivy

## 📋 Overview
This lab focuses on DevSecOps practices by integrating container image vulnerability scanning into the Docker workflow using **Trivy**. It demonstrates scanning an unoptimized image (`v1`), detecting high and critical security issues, and remediating them by migrating to a secure, lightweight base image (`v2`).

---

## 🛠️ Tools & Technologies Used
* **Containerization:** Docker
* **Security Scanner:** Trivy (v0.71.2)
* **OS Environment:** Ubuntu WSL2

---

## 🚀 Execution Steps

### 1. Clone Application Repository
Clone the application repository into the local Lab5 workspace:

```bash
git clone [https://github.com/Ibrahim-Adel15/TrivyImage.git](https://github.com/Ibrahim-Adel15/TrivyImage.git) .
```

---

### 2. Build Unoptimized Docker Image (v1)
Build the initial Docker image tagged as trivy-lab:v1 using the provided Dockerfile:

```bash
docker build -t trivy-lab:v1 .
```

---

### 3. Install and Verify Trivy
Install Trivy on the system and confirm the installation:

```bash
trivy --version
```

---

### 4. Scan Image for Security Vulnerabilities
Run a security scan on trivy-lab:v1 focusing specifically on HIGH and CRITICAL severity vulnerabilities:

```bash
trivy image --severity HIGH,CRITICAL trivy-lab:v1
```

---

### 5. Generate Initial HTML Security Report (v1)
Download the HTML report template and generate a comprehensive security report (report_v1.html) for the vulnerable image:

```bash
wget [https://raw.githubusercontent.com/aquasecurity/trivy/main/contrib/html.tpl](https://raw.githubusercontent.com/aquasecurity/trivy/main/contrib/html.tpl)
trivy image --severity HIGH,CRITICAL --format template --template "@html.tpl" -o report_v1.html trivy-lab:v1
```

---

## 🏆 Bonus: Vulnerability Remediation (v2)

### 6. Remediate Vulnerabilities in Dockerfile
Update the Dockerfile base image from a heavy, vulnerable Debian image (node:18-bullseye) to a lightweight, secure Alpine image (node:20-alpine):

```bash
FROM node:20-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 3000

CMD ["node", "app.js"]
```

---

### 7. Rebuild Optimized Image (v2)
Build the remediated Docker image tagged as ivolve-app:v2:

```bash
docker build -t ivolve-app:v2 .
```

---

### 8. Rescan Optimized Image and Generate Final Report
Rescan ivolve-app:v2 and export the clean report (report_v2.html) to verify that all HIGH and CRITICAL vulnerabilities have been resolved:

```bash
trivy image --severity HIGH,CRITICAL --format template --template "@html.tpl" -o report_v2.html ivolve-app:v2
```

---

## 🎯 Results Summary

| Metric | Image v1 (trivy-lab:v1) | Image v2 (ivolve-app:v2) |
| :--- | :--- | :--- |
| **Base OS** | Debian 11 (bullseye) | Alpine 3.23 |
| **Total Packages** | 409 | 18 |
| **HIGH / CRITICAL Issues** | Multiple Detected | **0 (Zero Vulnerabilities)** |
| **Security Status** | ❌ Vulnerable | ✅ Production Ready |

---
