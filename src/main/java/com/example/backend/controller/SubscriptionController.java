package com.example.backend.controller;

import com.example.backend.model.Subscription;
import com.example.backend.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<Subscription> subscribeCustomer(@RequestBody com.example.backend.model.Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<com.example.backend.model.Subscription> getCustomerSubscription(@PathVariable Long customerId) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Subscription> cancelSubscription(@PathVariable Long subscriptionId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(subscriptionId));
    }

    @GetMapping
    public ResponseEntity<List<com.example.backend.model.Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

}