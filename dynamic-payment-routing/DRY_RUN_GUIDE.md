# Dynamic Payment Gateway Routing - Dry Run Guide

This guide provides clear, step-by-step instructions to build, test, run, and perform a complete API dry run of the **Dynamic Payment Gateway Routing** service.

---

## 📋 1. Prerequisites

Ensure you have the following installed on your system:
- **Java 21 JDK**
- **Maven 3.9+**

### Setting Environment Variables (macOS / Linux)

```bash
# Set Java 21 Home
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home

# Add Java and Maven to PATH (adjust Maven path if installed elsewhere)
export PATH=$JAVA_HOME/bin:/usr/local/apache-maven-3.9.16/bin:$PATH

# Verify installation
java -version
mvn -version
```

---

## 🧪 2. Run Automated Build & Unit Tests

Before running the application server, verify that all unit and integration tests compile and pass cleanly:

```bash
mvn clean test
```

**Expected Output:**
```text
[INFO] Results:
[INFO] 
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

---

## 🚀 3. Start the Spring Boot Application Server

Launch the backend service locally on port `8080`:

```bash
mvn spring-boot:run
```

Once started, the application is listening at:
- **Base API URL**: `http://localhost:8080`
- **H2 Database Console**: `http://localhost:8080/h2-console`

---

## ⚡ 4. Execute the Dry Run

You can dry run all APIs using **Option A (Automated Script)**, **Option B (VS Code / IntelliJ REST Client)**, or **Option C (Manual cURL)**.

---

### Option A: Run the Automated Script (Recommended)

In a new terminal window, execute the pre-configured test script:

```bash
./curl-requests.sh
```

**What this script does:**
1. Sends `POST /api/v1/payments` to initiate a payment.
2. Sends `POST /api/v1/payments/demo/random` to trigger a demo transaction.
3. Sends `GET /api/v1/payments` to list all recorded transactions.
4. Sends `GET /api/v1/payments/{transactionId}` to check transaction status.
5. Sends `POST /api/v1/payments/webhooks/{gateway}` to process a manual webhook.
6. Sends `GET /api/v1/gateways/health` to view gateway health metrics.
7. Sends `GET /api/v1/gateways/{gateway}/health` to view specific gateway health.
8. Sends `POST /api/v1/gateways/{gateway}/reset-health` to reset gateway health.
9. Sends `PATCH /api/v1/gateways/{gateway}/weight` to update gateway routing weight.

---

### Option B: Use the `requests.http` REST File

If you are using **IntelliJ IDEA** or **VS Code** with the *REST Client* extension:
1. Open the [requests.http](file:///Users/meow/Desktop/dynamic-payment-routing/requests.http) file in your IDE.
2. Click **"Send Request"** above any request block to execute it interactively.

---

### Option C: Step-by-Step Manual cURL Testing

#### Step 4.1: Initiate a Payment
```bash
curl --request POST 'http://localhost:8080/api/v1/payments' \
  --header 'Content-Type: application/json' \
  --data '{
    "orderId": "ORDER-1001",
    "amount": 1499.00,
    "currency": "INR",
    "customerId": "CUSTOMER-501",
    "idempotencyKey": "ORDER-1001-PAYMENT-1"
  }'
```

#### Step 4.2: List All Payments
```bash
curl --request GET 'http://localhost:8080/api/v1/payments'
```

#### Step 4.3: Fetch Payment Status by Transaction ID
```bash
curl --request GET 'http://localhost:8080/api/v1/payments/<YOUR_TRANSACTION_ID>'
```

#### Step 4.4: Trigger Manual Gateway Webhook
```bash
curl --request POST 'http://localhost:8080/api/v1/payments/webhooks/RAZORPAY' \
  --header 'Content-Type: application/json' \
  --data '{
    "transactionId": "<YOUR_TRANSACTION_ID>",
    "gatewayPaymentId": "<YOUR_GATEWAY_PAYMENT_ID>",
    "status": "SUCCESS",
    "failureReason": null
  }'
```

#### Step 4.5: Check Gateway Health Statuses
```bash
curl --request GET 'http://localhost:8080/api/v1/gateways/health'
```

#### Step 4.6: Update Gateway Routing Weight
```bash
curl --request PATCH 'http://localhost:8080/api/v1/gateways/STRIPE/weight' \
  --header 'Content-Type: application/json' \
  --data '{"weight": 60}'
```

---

## 🗄️ 5. Inspect In-Memory H2 Database Console

1. Open your browser and navigate to: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
2. Enter the connection settings:
   - **Driver Class**: `org.h2.Driver`
   - **JDBC URL**: `jdbc:h2:mem:payments`
   - **User Name**: `sa`
   - **Password**: *(leave empty)*
3. Click **Connect**.
4. Run sample SQL queries:
   ```sql
   -- View all payment transactions
   SELECT * FROM PAYMENT_TRANSACTIONS;

   -- View gateway health states
   SELECT * FROM GATEWAY_HEALTH;
   ```

---

## ✅ Summary of Verification Checklist

- [x] Java 21 & Maven 3.9 environment active
- [x] All 4 unit/integration tests pass (`mvn test`)
- [x] Spring Boot server starts cleanly on port 8080 (`mvn spring-boot:run`)
- [x] `./curl-requests.sh` executes all 9 API calls successfully
- [x] Payments state transitions work (`CREATED` -> `PENDING` -> `SUCCESS`/`FAILED`)
- [x] Gateway health metrics and cooldown recovery function as expected
