# Dynamic Payment Gateway Routing

A clean Java 21 and Spring Boot implementation of dynamic payment gateway routing with an H2 in-memory database.

The solution covers the requirements from the coding-round problem:

- initiate payment transactions
- route payments using configurable gateway weights
- retry through another healthy gateway when a technical call fails
- mark a gateway unhealthy after consecutive failures
- automatically recover it after a cooldown
- receive asynchronous webhook updates
- automatically simulate webhook updates for mocked gateways

## Simple flow

```text
POST /api/v1/payments
        |
        v
Create payment with CREATED status
        |
        v
Choose a healthy gateway by weight
        |
        +---- technical failure ----> record failure and try another gateway
        |
        v
Gateway accepts payment
        |
        v
Save payment as PENDING 
        |
        v
MockWebhookSimulator waits 2-5 seconds
        |
        v
PaymentWebhookService updates it to SUCCESS or FAILED
```

## Project structure

```text
controller   REST APIs
service      payment flow, health logic and webhook processing
gateway      gateway interface and mocked gateway implementations
routing      routing interface and weighted implementation
repository   Spring Data JPA repositories
domain       payment and gateway-health entities
dto          API request and response records
config       routing, webhook and scheduler configuration
```

## Main design choices

- `PaymentGateway` is the strategy interface for Razorpay, Stripe and Cashfree.
- `GatewayRouter` is an interface implemented by `WeightedGatewayRouter`.
- `GatewayRegistry` resolves the correct gateway implementation.
- `GatewayHealthService` owns failure counting, unhealthy status and cooldown recovery.
- `PaymentService` only handles payment creation, routing and retry.
- `PaymentWebhookService` owns webhook validation and final status updates.
- `MockWebhookSimulator` behaves like an external gateway and sends an asynchronous result.
- Spring Data JPA repositories use H2, so no external database is required.

## Run

For detailed step-by-step instructions, see [DRY_RUN_GUIDE.md](file:///Users/meow/Desktop/dynamic-payment-routing/DRY_RUN_GUIDE.md).

Requirements:

- Java 21
- Maven 3.9+

```bash
mvn clean test
mvn spring-boot:run
```

Application:

```text
http://localhost:8080
```

H2 console:

```text
http://localhost:8080/h2-console
```

H2 connection values:

```text
JDBC URL: jdbc:h2:mem:payments
Username: sa
Password: empty
```

---

## Complete API Reference (POST, GET, PATCH)

You can run all APIs automatically using the provided shell script or REST client file:
- **Bash Script**: `./curl-requests.sh`
- **REST Client File**: `requests.http`

### 1. Payment APIs (`/api/v1/payments`)

#### 1.1 Initiate Payment Transaction (`POST /api/v1/payments`)
**Request:**
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

**Response (`201 Created`):**
```json
{
  "transactionId": "generated-transaction-id",
  "orderId": "ORDER-1001",
  "amount": 1499.00,
  "currency": "INR",
  "gateway": "RAZORPAY",
  "gatewayPaymentId": "razorpay_pay_generated-id",
  "status": "PENDING",
  "routingAttempts": 1,
  "failureReason": null
}
```

#### 1.2 Initiate Payment with Random Demo Data (`POST /api/v1/payments/demo/random`)
**Request:**
```bash
curl --request POST 'http://localhost:8080/api/v1/payments/demo/random'
```

#### 1.3 Get All Payment Transactions (`GET /api/v1/payments`)
**Request:**
```bash
curl --request GET 'http://localhost:8080/api/v1/payments'
```

**Response (`200 OK`):**
```json
[
  {
    "transactionId": "generated-transaction-id",
    "orderId": "ORDER-1001",
    "amount": 1499.00,
    "currency": "INR",
    "gateway": "RAZORPAY",
    "gatewayPaymentId": "razorpay_pay_generated-id",
    "status": "SUCCESS",
    "routingAttempts": 1,
    "failureReason": null
  }
]
```

#### 1.4 Get Single Payment Transaction Details by ID (`GET /api/v1/payments/{transactionId}`)
**Request:**
```bash
curl --request GET 'http://localhost:8080/api/v1/payments/generated-transaction-id'
```

---

### 2. Webhook APIs (`/api/v1/payments/webhooks`)

#### 2.1 Receive Gateway Webhook Status Update (`POST /api/v1/payments/webhooks/{gateway}`)
**Request:**
```bash
curl --request POST 'http://localhost:8080/api/v1/payments/webhooks/RAZORPAY' \
  --header 'Content-Type: application/json' \
  --data '{
    "transactionId": "generated-transaction-id",
    "gatewayPaymentId": "razorpay_pay_generated-id",
    "status": "SUCCESS",
    "failureReason": null
  }'
```

**Allowed webhook status values:** `SUCCESS`, `FAILED`

---

### 3. Gateway Admin & Health APIs (`/api/v1/gateways`)

#### 3.1 Get Health Status of All Gateways (`GET /api/v1/gateways/health`)
**Request:**
```bash
curl --request GET 'http://localhost:8080/api/v1/gateways/health'
```

**Response (`200 OK`):**
```json
[
  {
    "gateway": "CASHFREE",
    "weight": 20,
    "status": "HEALTHY",
    "consecutiveFailures": 0,
    "unhealthyUntil": null,
    "updatedAt": "2026-08-05T16:30:00Z"
  },
  {
    "gateway": "RAZORPAY",
    "weight": 50,
    "status": "HEALTHY",
    "consecutiveFailures": 0,
    "unhealthyUntil": null,
    "updatedAt": "2026-08-05T16:30:00Z"
  },
  {
    "gateway": "STRIPE",
    "weight": 30,
    "status": "HEALTHY",
    "consecutiveFailures": 0,
    "unhealthyUntil": null,
    "updatedAt": "2026-08-05T16:30:00Z"
  }
]
```

#### 3.2 Get Health Status of a Specific Gateway (`GET /api/v1/gateways/{gateway}/health`)
**Request:**
```bash
curl --request GET 'http://localhost:8080/api/v1/gateways/RAZORPAY/health'
```

#### 3.3 Reset Gateway Health to HEALTHY (`POST /api/v1/gateways/{gateway}/reset-health`)
**Request:**
```bash
curl --request POST 'http://localhost:8080/api/v1/gateways/RAZORPAY/reset-health'
```

#### 3.4 Update Gateway Weight (`PATCH /api/v1/gateways/{gateway}/weight`)
**Request:**
```bash
curl --request PATCH 'http://localhost:8080/api/v1/gateways/STRIPE/weight' \
  --header 'Content-Type: application/json' \
  --data '{"weight": 60}'
```
*(Setting a weight of `0` disables the gateway for routing without deleting it).*

---

## Configuration

```yaml
payment:
  routing:
    failure-threshold: 3
    cooldown: 30s
    max-attempts: 3

  mock-webhook:
    enabled: true
    min-delay: 2s
    max-delay: 5s
    success-rate: 0.85
```

Each configured gateway has:

- `weight`: routing share
- `success-rate`: probability that the mock initiation call is technically successful
- `min-delay-ms` and `max-delay-ms`: mock network latency

The webhook `success-rate` represents the final mocked payment result.

## Important production note

In a real integration, Razorpay, Stripe or Cashfree sends the webhook HTTP request. The mock simulator should then be disabled:

```yaml
payment:
  mock-webhook:
    enabled: false
```

A production webhook implementation should also verify the gateway signature before updating the payment.

A final payment failure does not automatically mark a gateway unhealthy because it may be caused by a declined card, insufficient balance or another customer-level reason. Gateway health is changed only for technical initiation failures.
