# 🚀 Building a Production-Ready 3-Node Kubernetes Cluster from Scratch

Welcome to my DevOps lab documentation! In this project, I successfully provisioned, configured, and managed a fully functional **3-node Kubernetes cluster** (1 Master and 2 Worker nodes) on Ubuntu Virtual Machines. We deployed core networking, workloads, monitoring tools, and high-performance cluster management interfaces.

---

## 🏗️ Architecture & Environment Stack
- **Virtualization:** Oracle VirtualBox (3 Ubuntu VMs: 1 Master, 2 Workers)
- **Networking:** Bridged Networking + Calico CNI
- **Container Runtime:** `containerd`
- **Orchestration:** Kubernetes v1.30 (`kubeadm`, `kubelet`, `kubectl`)
- **Monitoring & Management:** Metrics Server & K9s CLI

---

## 📸 Lab Step-by-Step Implementation & Documentation

### Step 1: Infrastructure Provisioning & Virtual Machines Setup
We started by creating three Ubuntu Virtual Machines with proper CPU, RAM, and bridged networking configurations to ensure smooth communication across the nodes.
- **Visual Evidence:**
  ![Create 3 VMs](create-3-VMs-master-and-2-worker-nodes.png)

### Step 2: System Configuration & Kernel Parameters (`sysctl`)
Configured necessary kernel modules (`br_netfilter`) and system network parameters to allow iptables to transparently see bridged traffic across Master and Worker nodes.
- **Master Node Configuration:**
  ![Sysctl Master Config](sysctl-master-config.png)
- **Worker Node 1 Configuration:**
  ![Sysctl Worker 1 Config](sysctl-worker-1-config.png)
- **Worker Node 2 Configuration:**
  ![Sysctl Worker 2 Config](sysctl-worker-2-config.png)

### Step 3: Container Runtime (`containerd`) & Kubernetes Tools Installation
Installed `containerd` as our enterprise container runtime, configured its cgroups driver, and installed the core Kubernetes toolset (`kubeadm`, `kubelet`, `kubectl`) version 1.30.
- **Containerd Installation:**
  ![Containerd Installation](Installation-and-implementation-containerd.png)
- **Kubernetes Tools Installation:**
  ![K8s Tools Installed](k8s-tools-installed.png)

### Step 4: Cluster Initialization (`kubeadm init`) & Worker Nodes Joining
Initialized the control plane on the Master node using `kubeadm init`, copied the admin cluster configuration, and securely joined both worker nodes to the cluster.
- **Kubeadm Init Success:**
  ![Kubeadm Init Success](kubeadm-init-success.jpeg)
- **Workers Joined Successfully:**
  ![Workers Joined](workers-joined.png)

### Step 5: Cluster Health & Readiness Verification
Verified that all cluster nodes transitioned into the `Ready` state with Calico CNI fully operational for pod-to-pod networking.
- **Cluster Ready Status:**
  ![Cluster Ready](Cluster-is-ready-now.png)
- **Detailed Nodes Status:**
  ![Nodes Details](nodes-details.png)

### Step 6: Deploying Sample Application (Nginx Workload)
Deployed an Nginx workload structured with replicas and exposed it via `NodePort` to test intra-cluster load balancing and external connectivity.
- **Nginx Application Deployment:**
  ![Nginx App](nginx-app.png)

### Step 7: Resource Monitoring with Metrics Server
Installed the Kubernetes Metrics Server, patched it to bypass insecure TLS with kubelets (`--kubelet-insecure-tls`), and validated real-time resource tracking (`kubectl top nodes`).
- **Metrics Server Validation:**
  ![Metrics Server](metrics-server.png)

---

## 🏆 Final Result & High-Performance Management (K9s CLI)
To achieve ultimate cluster observability and overcome traditional dashboard proxy limitations, we integrated **K9s CLI** as our primary high-performance monitoring interface, providing live tracking of pods, nodes, and resource usage.

---

## 👤 Author
**Mohamed**  
*Aspiring DevOps Engineer*
