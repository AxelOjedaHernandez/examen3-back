package com.agoh.backend.controller;

import com.agoh.backend.dto.CustomResponse;
import com.agoh.backend.entities.Promotion;
import com.agoh.backend.services.PromotionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/promotion")
public class PromotionController {

    @Autowired
    private PromotionService PromotionService;

    @GetMapping("/listar")
    public ResponseEntity<CustomResponse<List<Promotion>>> getPromotions() {
        List<Promotion> Promotions = new ArrayList<>();
        Link allPromotionsLink = linkTo(PromotionController.class).withSelfRel();
        List<Link> links = List.of(allPromotionsLink);

        try {
            Promotions = PromotionService.getPromotions();
            if (!Promotions.isEmpty()) {
                CustomResponse<List<Promotion>> response = new CustomResponse<>(
                        1, "Promotions encontrados", Promotions, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(
                        0, "Promotions no encontrados", Promotions, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Promotion>> response = new CustomResponse<>(
                    0, "Error interno de servidor", Promotions, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get Promotion by ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<CustomResponse<Promotion>> getPromotionById(@PathVariable("id") Long id) {
        Optional<Promotion> Promotion = PromotionService.getPromotionById(id);
        Link allPromotionsLink = linkTo(PromotionController.class).withSelfRel();
        List<Link> links = List.of(allPromotionsLink);

        if (Promotion.isPresent()) {
            CustomResponse<Promotion> response = new CustomResponse<>(1, "Promotion encontrado", Promotion.get(),
                    links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Promotion> response = new CustomResponse<>(0, "Promotion no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Create Promotion
    @PostMapping("/crear")
    public ResponseEntity<CustomResponse<Promotion>> createPromotion(@RequestBody Promotion Promotion) {
        Link allPromotionsLink = linkTo(PromotionController.class).withSelfRel();
        List<Link> links = List.of(allPromotionsLink);

        try {
            Promotion createdPromotion = PromotionService.createPromotion(Promotion);
            CustomResponse<Promotion> response = new CustomResponse<>(1, "Promotion creado", createdPromotion,
                    links);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            CustomResponse<Promotion> response = new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update Promotion
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CustomResponse<Promotion>> updatePromotion(@RequestBody Promotion Promotion,
            @PathVariable Long id) {
        Link allPromotionsLink = linkTo(PromotionController.class).withSelfRel();
        List<Link> links = List.of(allPromotionsLink);

        Promotion.setId(id);
        if (PromotionService.getPromotionById(id).isPresent()) {
            Promotion updatedPromotion = PromotionService.updatePromotion(Promotion);
            CustomResponse<Promotion> response = new CustomResponse<>(1, "Promotion actualizado",
                    updatedPromotion, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Promotion> response = new CustomResponse<>(0, "Promotion no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete Promotion by ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<CustomResponse<Void>> deletePromotionById(@PathVariable Long id) {
        Link allPromotionsLink = linkTo(PromotionController.class).withSelfRel();
        List<Link> links = List.of(allPromotionsLink);

        if (PromotionService.getPromotionById(id).isPresent()) {
            PromotionService.deletePromotionById(id);
            CustomResponse<Void> response = new CustomResponse<>(1, "Promotion eliminado", null, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Void> response = new CustomResponse<>(0, "Promotion no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
