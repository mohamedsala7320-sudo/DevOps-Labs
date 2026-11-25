# Lab 22: Jenkins Agents and Shared Libraries

## Objective
Move the Lab 21 pipeline logic into a Jenkins Shared Library and run the pipeline on a Jenkins agent (node-1).

## Shared Library (`vars/`)
| File | What it does |
|------|--------------|
| `BuildApp.groovy` | Runs unit tests and builds the app with Maven |
| `BuildImage.groovy` | Builds the Docker image, scans it with Trivy, pushes it to DockerHub, deletes it locally |
| `DeployOnK8s.groovy` | Updates the image in `k8s/deployment.yaml` and deploys to the cluster |

The library lives in a separate repo (`jenkins-shared-library`) and is registered in
Manage Jenkins -> System -> Global Trusted Pipeline Libraries as `my-shared-lib`.

## Jenkinsfile
The pipeline loads the library with `@Library('my-shared-lib') _` and calls
`BuildApp()`, `BuildImage(...)` and `DeployOnK8s(...)`. It runs on the agent with `agent { label 'node-1' }`.

## Agent setup (node-1)
Installed and configured on node-1: Java 21, Maven, Docker, Trivy, kubectl, `~/.kube/config`,
and user `devboy` added to the `docker` group. The agent runs from `/home/devboy/jenkins-agent`.

## Problems and solutions
| Problem | Cause | Solution |
|---------|-------|----------|
| `trivy: command not found` on node-1 | Trivy was only installed on the master | Installed Trivy on node-1 |
| Agent kept disconnecting with `UnsupportedClassVersionError` (class file version 65.0) | node-1 had Java 11/17 while the controller runs Java 21 | Installed OpenJDK 21 on node-1 |
| Docker commands would fail on node-1 | User was not in the `docker` group | `sudo usermod -aG docker devboy` and re-login |
| `kubectl` could not reach the cluster from node-1 | No kubeconfig on node-1 | Copied `~/.kube/config` from the master with `scp` |

## Result
Build #2 ran on node-1 and succeeded. The new pods are running in the `demo` namespace.

## Screenshots
Available in the `screenshots/` folder (01 - 17).
