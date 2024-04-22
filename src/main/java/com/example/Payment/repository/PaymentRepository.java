/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Payment.repository;

import com.example.Payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 *
 * @author User
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

    @Query("select SUM(pr.amount) from Payment pr where pr.projectId=?1")
    BigDecimal getTotalAmountByProjectId(String projectId);

}
