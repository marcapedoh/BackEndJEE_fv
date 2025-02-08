package com.example.backend.controller;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private com.example.backend.service.SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<com.example.backend.model.Subscription> subscribeCustomer(@RequestBody com.example.backend.model.Subscription subscription) {
        com.example.backend.model.Subscription createdSubscription = subscriptionService.subscribeCustomer(subscription);
        return ResponseEntity.ok(createdSubscription);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<com.example.backend.model.Subscription> getCustomerSubscription(@PathVariable Long customerId) {
        com.example.backend.model.Subscription subscription = subscriptionService.getCustomerSubscription(customerId);
        return ResponseEntity.ok(subscription);
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<com.example.backend.model.Subscription>> getAllSubscriptions() {
        List<com.example.backend.model.Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }
}