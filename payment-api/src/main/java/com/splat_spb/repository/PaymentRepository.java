package com.splat_spb.repository;

import com.splat_spb.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Default comment.
 **/
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

}
