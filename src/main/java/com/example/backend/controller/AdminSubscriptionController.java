package com.example.backend.controller;

import com.example.backend.model.Subscription;
import com.example.backend.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "admin/subscriptions")
public class AdminSubscriptionController {

    private final SubscriptionService subscriptionService;

    public AdminSubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long subscriptionId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(subscriptionId));
    }

    @PutMapping("/{subscriptionId}/enable")
    public ResponseEntity<Subscription> enableSubscription(@PathVariable Long subscriptionId) {
        return ResponseEntity.ok(subscriptionService.enableSubscription(subscriptionId));
    }

    @PutMapping("/{subscriptionId}/disable")
    public ResponseEntity<Subscription> disableSubscription(@PathVariable Long subscriptionId) {
        return ResponseEntity.ok(subscriptionService.disableSubscription(subscriptionId));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

}