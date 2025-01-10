package com.agoh.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agoh.backend.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {   
}
