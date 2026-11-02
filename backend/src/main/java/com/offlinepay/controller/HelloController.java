package com.offlinepay.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
}

