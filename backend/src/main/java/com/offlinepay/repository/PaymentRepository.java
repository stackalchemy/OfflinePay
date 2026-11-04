
package com.offlinepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offlinepay.entity.PaymentTransaction;

public interface PaymentRepository
        extends JpaRepository<PaymentTransaction, Long> {

}
