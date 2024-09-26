package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CheckoutRequest;
import com.udemy.elearning.mapper.CheckoutResponse;
import com.udemy.elearning.models.Checkout;
import com.udemy.elearning.services.CheckoutService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping()
    public ResponseEntity<Checkout> create(@RequestBody CheckoutRequest checkoutRequest) throws BadRequestException {
        Checkout checkout = checkoutService.create(checkoutRequest);
        return ResponseEntity.ok(checkout);
    }

    @GetMapping()
    public ResponseEntity<List<Checkout>> getAll() {
        List<Checkout> checkoutList = checkoutService.findAll();
        return ResponseEntity.ok(checkoutList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checkout> getById(@PathVariable(value = "id") Long id) {
        Checkout checkout = checkoutService.findById(id);
        return ResponseEntity.ok(checkout);
    }

}
