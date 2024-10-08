package com.udemy.elearning.repository;

import com.udemy.elearning.dto.CheckoutRequest;
import com.udemy.elearning.models.Checkout;
import com.udemy.elearning.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    List<Checkout> findByUserId(Long userId);
}
