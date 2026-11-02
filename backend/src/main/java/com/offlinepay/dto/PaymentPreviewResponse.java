package com.offlinepay.dto;

import java.math.BigDecimal;

public record PaymentPreviewResponse(
        String message,
        Long senderId,
        Long receiverId,
        BigDecimal amount,
        String currency,
        String status
) {
}