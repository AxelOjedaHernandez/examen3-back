package com.agoh.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agoh.backend.entities.Promotion;
import com.agoh.backend.repositories.PromotionRepository;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository PromotionRepository;

    public List<Promotion> getPromotions() {
        return PromotionRepository.findAll();
    }

    public Optional<Promotion> getPromotionById(Long id) {
        return PromotionRepository.findById(id);
    }

    public Promotion createPromotion(Promotion Promotion) {
        return PromotionRepository.save(Promotion);
    }

    public Promotion updatePromotion(Promotion Promotion) {
        return PromotionRepository.save(Promotion);
    }

    public void deletePromotionById(Long id) {
        PromotionRepository.deleteById(id);
    }
}
