package com.udemy.elearning.repository;

import com.udemy.elearning.models.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {
}
