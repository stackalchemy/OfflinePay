package com.offlinepay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.offlinepay.dto.PaymentResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.offlinepay.dto.UserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.offlinepay.dto.PaymentRequest;
import com.offlinepay.dto.PaymentPreviewResponse;
import com.offlinepay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import com.offlinepay.entity.PaymentTransaction;
import com.offlinepay.dto.PaymentResponse;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to OfflinePay Backend!";
 }
    
    @PostMapping("/greet")
    public String greet(@RequestBody UserRequest request) {

        return "Hello " + request.getName()
                + "! Welcome to OfflinePay.";
    }
    
    @GetMapping("/status")
    public ResponseEntity<String> status() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OfflinePay Backend is healthy!");
    }
    
    @PostMapping("/payments/preview")
    public ResponseEntity<PaymentPreviewResponse> previewPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentPreviewResponse response = paymentService.previewPayment(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    
               
    }
    @Autowired
    private PaymentService paymentService;
    
    
    

@PostMapping("/payments")
public ResponseEntity<PaymentResponse> createPayment(
        @Valid @RequestBody PaymentRequest request) {

    PaymentTransaction savedTransaction =
            paymentService.createPayment(request);

    PaymentResponse response = new PaymentResponse(
        savedTransaction.getId(),
        savedTransaction.getSenderId(),
        savedTransaction.getReceiverId(),
        savedTransaction.getAmount(),
        savedTransaction.getCurrency(),
        savedTransaction.getStatus(),
        "Payment transaction created successfully"
    );

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
}

//Get all payment transactions
@GetMapping("/payments")
public ResponseEntity<List<PaymentResponse>> getAllPayments() {

 List<PaymentResponse> payments =
         paymentService.getAllPayments();

 return ResponseEntity.ok(payments);
}

//Get a payment transaction by ID
@GetMapping("/payments/{id}")
public ResponseEntity<PaymentResponse> getPaymentById(
     @PathVariable Long id) {

 PaymentResponse payment =
         paymentService.getPaymentById(id);

 return ResponseEntity.ok(payment);
}


}

