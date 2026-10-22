# Lab 6: Docker Environment Variables and Resource Management

## 📋 Overview
This lab covers essential Docker runtime configurations, focusing on container resource limits (CPU and Memory) and three distinct techniques for injecting environment variables into containers:
1. Direct CLI flags (`--env`)
2. Environment files (`--env-file`)
3. Build-time Dockerfile instructions (`ENV`)

---

## 🛠️ Tools & Technologies Used
* **Base Image:** `python:3.12-slim`
* **Container Engine:** Docker Engine
* **Resource Limits:** 1 CPU, 256 MB RAM

---

## 🚀 Execution Steps

### 1. Application Setup & Initial Build
Clone the Python web application repository and create the base `Dockerfile`:

```dockerfile
FROM python:3.12-slim

WORKDIR /app

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

COPY . .

EXPOSE 5000

CMD ["python", "app.py"]
```

Build the initial Docker image ivolve1:

```bash
docker build -t ivolve1 .
```

---

### 2. Method 1: CLI Environment Variables & Resource Limits

Run container1 with direct environment variables (APP_NAME=iVolveApp1, APP_ENV=stg) and resource boundaries (1 CPU, 256 MB RAM):
```bash
docker run -d --name container1 -p 5001:5000 \
  --env APP_NAME=iVolveApp1 \
  --env APP_ENV=stg \
  --cpus="1.0" -m 256m ivolve1
```
Verification:
```bash
curl http://localhost:5001
docker stats container1 --no-stream
```

---

### 3. Method 2: External Environment File (--env-file)

Create an env.txt configuration file:
```bash
APP_NAME=iVolveApp2
APP_ENV=prod
```
Run container2 loading variables from the file:
```bash
docker run -d --name container2 -p 5002:5000 \
  --env-file env.txt \
  --cpus="1.0" -m 256m ivolve1
```
Verification:
```bash
curl http://localhost:5002
```
---

### 4. Method 3: Hardcoded Dockerfile Environment Variables

Update the Dockerfile to embed environment variables natively:
```bash
FROM python:3.12-slim

WORKDIR /app

ENV APP_NAME=iVolveApp3
ENV APP_ENV=dev

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY . .

EXPOSE 5000
CMD ["python", "app.py"]
```
Build the updated image ivolve2 and execute container3:
```bash
docker build -t ivolve2 .

docker run -d --name container3 -p 5003:5000 \
  --cpus="1.0" -m 256m ivolve2
```

Verification:
```bash
curl http://localhost:5003
```

## 📊 Summary Comparison

| Container | Image | Config Source | APP_NAME | APP_ENV | Port | Resource Limit | Status |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **container1** | `ivolve1` | Direct CLI `--env` | `iVolveApp1` | `stg` | `5001` | 1 CPU / 256 MB | ✅ Running |
| **container2** | `ivolve1` | File `--env-file` | `iVolveApp2` | `prod` | `5002` | 1 CPU / 256 MB | ✅ Running |
| **container3** | `ivolve2` | Dockerfile `ENV` | `iVolveApp3` | `dev` | `5003` | 1 CPU / 256 MB | ✅ Running |
