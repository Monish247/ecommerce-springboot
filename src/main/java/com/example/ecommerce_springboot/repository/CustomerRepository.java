package com.example.ecommerce_springboot.repository;

import com.example.ecommerce_springboot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);
	Customer findByResetToken(String token);  
}