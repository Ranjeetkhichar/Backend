#!/usr/bin/env bash
# ==============================================================================
# Dynamic Payment Gateway Routing - API Requests Script
# ==============================================================================
BASE_URL="http://localhost:8080"

echo "======================================================================"
echo " 1. POST /api/v1/payments - Initiate Payment"
echo "======================================================================"
INIT_RESPONSE=$(curl -s -X POST "$BASE_URL/api/v1/payments" \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": "ORDER-9999",
    "amount": 2499.50,
    "currency": "INR",
    "customerId": "CUST-888",
    "idempotencyKey": "IDEM-ORDER-9999-1"
  }')
echo "$INIT_RESPONSE"
echo ""

TRANSACTION_ID=$(echo "$INIT_RESPONSE" | grep -o '"transactionId":"[^"]*' | cut -d'"' -f4)
GATEWAY=$(echo "$INIT_RESPONSE" | grep -o '"gateway":"[^"]*' | cut -d'"' -f4)
GATEWAY_PAYMENT_ID=$(echo "$INIT_RESPONSE" | grep -o '"gatewayPaymentId":"[^"]*' | cut -d'"' -f4)

echo "Extracted Transaction ID: $TRANSACTION_ID"
echo "Extracted Gateway: $GATEWAY"
echo "Extracted Gateway Payment ID: $GATEWAY_PAYMENT_ID"
echo ""

echo "======================================================================"
echo " 2. POST /api/v1/payments/demo/random - Initiate Random Demo Payment"
echo "======================================================================"
curl -s -X POST "$BASE_URL/api/v1/payments/demo/random"
echo ""
echo ""

echo "======================================================================"
echo " 3. GET /api/v1/payments - Get All Payment Transactions"
echo "======================================================================"
curl -s -X GET "$BASE_URL/api/v1/payments"
echo ""
echo ""

if [ -n "$TRANSACTION_ID" ]; then
  echo "======================================================================"
  echo " 4. GET /api/v1/payments/{transactionId} - Get Payment Status by ID"
  echo "======================================================================"
  curl -s -X GET "$BASE_URL/api/v1/payments/$TRANSACTION_ID"
  echo ""
  echo ""

  echo "======================================================================"
  echo " 5. POST /api/v1/payments/webhooks/{gateway} - Receive Gateway Webhook"
  echo "======================================================================"
  curl -s -X POST "$BASE_URL/api/v1/payments/webhooks/$GATEWAY" \
    -H "Content-Type: application/json" \
    -d "{
      \"transactionId\": \"$TRANSACTION_ID\",
      \"gatewayPaymentId\": \"$GATEWAY_PAYMENT_ID\",
      \"status\": \"SUCCESS\",
      \"failureReason\": null
    }"
  echo ""
  echo ""
fi

echo "======================================================================"
echo " 6. GET /api/v1/gateways/health - Get Health of All Gateways"
echo "======================================================================"
curl -s -X GET "$BASE_URL/api/v1/gateways/health"
echo ""
echo ""

echo "======================================================================"
echo " 7. GET /api/v1/gateways/{gateway}/health - Get Specific Gateway Health"
echo "======================================================================"
curl -s -X GET "$BASE_URL/api/v1/gateways/RAZORPAY/health"
echo ""
echo ""

echo "======================================================================"
echo " 8. POST /api/v1/gateways/{gateway}/reset-health - Reset Gateway Health"
echo "======================================================================"
curl -s -X POST "$BASE_URL/api/v1/gateways/RAZORPAY/reset-health"
echo ""
echo ""

echo "======================================================================"
echo " 9. PATCH /api/v1/gateways/{gateway}/weight - Update Gateway Weight"
echo "======================================================================"
curl -s -X PATCH "$BASE_URL/api/v1/gateways/STRIPE/weight" \
  -H "Content-Type: application/json" \
  -d '{"weight": 60}'
echo ""
echo ""
