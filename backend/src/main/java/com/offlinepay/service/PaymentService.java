
package com.offlinepay.service;

import org.springframework.stereotype.Service;

import com.offlinepay.dto.PaymentRequest;
import com.offlinepay.dto.PaymentResponse;
import com.offlinepay.dto.PaymentPreviewResponse;
import com.offlinepay.entity.PaymentTransaction;
import com.offlinepay.repository.PaymentRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // Constructor Injection
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Existing preview functionality
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

    // Create and save a payment transaction
    public PaymentTransaction createPayment(PaymentRequest request) {

        PaymentTransaction transaction = new PaymentTransaction();

        transaction.setSenderId(request.getSenderId());
        transaction.setReceiverId(request.getReceiverId());
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setStatus("PENDING");

        return paymentRepository.save(transaction);
    }

 // Retrieve all payment transactions
 public List<PaymentResponse> getAllPayments() {

     List<PaymentTransaction> transactions =
             paymentRepository.findAll();

     return transactions.stream()
             .map(this::convertToResponse)
             .collect(Collectors.toList());
 }

 // Retrieve one payment transaction by ID
 public PaymentResponse getPaymentById(Long id) {

     PaymentTransaction transaction =
             paymentRepository.findById(id)
                 .orElseThrow(() ->
                     new ResponseStatusException(
                         HttpStatus.NOT_FOUND,
                         "Payment transaction not found"
                     )
                 );

     return convertToResponse(transaction);
 }

 // Convert Entity into Response DTO
 private PaymentResponse convertToResponse(
         PaymentTransaction transaction) {

     return new PaymentResponse(
         transaction.getId(),
         transaction.getSenderId(),
         transaction.getReceiverId(),
         transaction.getAmount(),
         transaction.getCurrency(),
         transaction.getStatus(),
         "Payment transaction retrieved successfully"
     );
 }

}
