# Lab 11: Namespace Management and Resource Quota Enforcement

## 🏗️ Architecture Overview

The following diagram illustrates how the `ResourceQuota` operates within the isolated `ivolve` namespace to strictly limit total active pod instances to a maximum of 3:

```mermaid
graph TD
    subgraph Cluster["Kubernetes Cluster"]
        subgraph DefaultNS["default Namespace"]
            DP["Unrestricted Pods"]
        end

        subgraph IvolveNS["ivolve Namespace (Isolated)"]
            RQ["ResourceQuota: ivolve-quota<br/>(hard limit: pods=3)"]
            
            P1["pod-1 (Running)"]
            P2["pod-2 (Running)"]
            P3["pod-3 (Running)"]
            P4["pod-4 (Rejected)"]

            RQ -. Enforces Limit .-> P1
            RQ -. Enforces Limit .-> P2
            RQ -. Enforces Limit .-> P3
            RQ -- Blocked (Forbidden) --> P4
        end
    end

    style IvolveNS fill:#1a202c,stroke:#41b883,stroke-width:2px,color:#fff
    style RQ fill:#2d3748,stroke:#e53e3e,stroke-width:2px,color:#fff
    style P4 fill:#742a2a,stroke:#feb2b2,stroke-width:1px,color:#fff
```

## 🎯 Lab Objectives

1. **Namespace Isolation**: Create a dedicated Kubernetes namespace (`ivolve`) to isolate application workloads and administrative boundaries.
2. **Resource Quota Enforcement**: Define and apply a `ResourceQuota` named `ivolve-quota` restricting the maximum number of concurrent pods inside the namespace to **3**.
3. **Quota Verification & Testing**: Deploy workloads up to the defined quota limit and verify that Kubernetes active admission control rejects any deployment exceeding the threshold.

---

## 🛠️ Step-by-Step Implementation

### Step 1: Create the Isolated Namespace

First, create the target `ivolve` namespace to host the restricted environment:

```bash
kubectl create namespace ivolve
```

![Create Namespace and Quota Manifest](screenshots/create-namespace-and-quota-yaml-file.png)

---

### Step 2: Define and Apply the ResourceQuota Manifest

Create the `ivolve-quota.yaml` manifest specifying the `pods: "3"` constraint under the `spec.hard` field.

> **Note**: Resource names in Kubernetes must conform to RFC 1123 standards (lowercase letters, numbers, or hyphens).

**Manifest File (`ivolve-quota.yaml`):**

```yaml
apiVersion: v1
kind: ResourceQuota
metadata:
  name: ivolve-quota
  namespace: ivolve
spec:
  hard:
    pods: "3"
```

Apply the quota definition to the `ivolve` namespace:

```bash
kubectl apply -f ivolve-quota.yaml
```

![Apply Resource Quota Manifest](screenshots/apply-quota-file.png)

---

### Step 3: Deploy Workloads & Test Quota Enforcement

1. **Deploy Pod 1 to 3** up to the hard limit threshold:

   ```bash
   kubectl run pod-1 --image=nginx --namespace=ivolve
   kubectl run pod-2 --image=nginx --namespace=ivolve
   kubectl run pod-3 --image=nginx --namespace=ivolve
   ```

2. **Verify Pod Status**:

   ```bash
   kubectl get pods -n ivolve
   ```

3. **Trigger Quota Rejection**: Attempt to create a 4th pod exceeding the quota limit:

   ```bash
   kubectl run pod-4 --image=nginx --namespace=ivolve
   ```

   **Expected Output (Forbidden Error):**
   ```text
   Error from server (Forbidden): pods "pod-4" is forbidden: exceeded quota: ivolve-quota, requested: pods=1, used: pods=3, limited: pods=3
   ```

![Verify Limit Resource Quota Rejection](screenshots/verify-limit-resource-quota.png)

---

### Step 4: Inspect Quota Usage Details (Bonus Verification)

Inspect the live resource usage against configured hard limits within the namespace:

```bash
kubectl describe resourcequota ivolve-quota -n ivolve
```

**Output Details:**
* **Resource**: `pods`
* **Used**: `3`
* **Hard**: `3`

![Describe Resource Quota Usage](screenshots/describe-usage.png)

---

## 📝 Conclusion

Through the implementation of `ResourceQuota` resources, multi-tenant cluster environments can enforce strict object limits per namespace. This prevents resource starvation, guarantees capacity management, and blocks unauthorized pod expansions.
