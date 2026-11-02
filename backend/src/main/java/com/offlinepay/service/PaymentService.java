package com.offlinepay.service;

import org.springframework.stereotype.Service;

import com.offlinepay.dto.PaymentRequest;
import com.offlinepay.dto.PaymentPreviewResponse;

@Service
public class PaymentService {

    public PaymentPreviewResponse previewPayment(PaymentRequest request) {

        return new PaymentPreviewResponse(
                "Payment preview generated successfully",
                request.getSenderId(),
                request.getReceiverId(),
                request.getAmount(),
                request.getCurrency(),
                "PENDING"
        );
    }
}