package com.example.backend.service;

import com.example.backend.model.Subscription;
import com.example.backend.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final PackService packService;
    private final CustomerService customerService;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, PackService packService, CustomerService customerService) {
        this.subscriptionRepository = subscriptionRepository;
        this.packService = packService;
        this.customerService = customerService;
    }

    public Subscription createSubscription(Subscription subscription) {
        subscription.setEnabled(true);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        subscriptions.forEach(this::setSubscriptionInfos);
        return subscriptions;
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .map(this::setSubscriptionInfos)
                .orElseThrow(
                        () -> new RuntimeException("Subscription not found!!")
                );
    }

    public Subscription disableSubscription(Long id) {
        Subscription subscription = subscriptionRepository.getReferenceById(id);
        subscription.setEnabled(false);
        subscription = subscriptionRepository.save(subscription);
        return subscription;
    }

    private Subscription setSubscriptionInfos(Subscription subscription) {
        subscription.setPack(packService.getPackById(subscription.getPackId()));
        subscription.setCustomer(customerService.getCustomerById(subscription.getCustomerId()));
        return subscription;
    }

}