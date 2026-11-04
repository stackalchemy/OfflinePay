
package com.offlinepay.dto;

import java.math.BigDecimal;

public record PaymentResponse(
    Long transactionId,
    Long senderId,
    Long receiverId,
    BigDecimal amount,
    String currency,
    String status,
    String message
) {}
