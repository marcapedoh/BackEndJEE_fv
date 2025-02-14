package com.example.backend.service;

import com.example.backend.model.Pack;
import com.example.backend.repository.PackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackService {

    private final PackRepository packRepository;

    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    public Pack addPack(Pack pack) {
        return packRepository.save(pack);
    }

    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }

    public Pack getPackById(Long id) {
        return packRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pack not found")
        );
    }

    public Pack updatePack(Long id, Pack packDetails) {
        Pack pack = packRepository.findById(id).orElseThrow(() -> new RuntimeException("Pack not found"));
        pack.setOfferName(packDetails.getOfferName());
        pack.setDurationMonths(packDetails.getDurationMonths());
        pack.setMonthlyPrice(packDetails.getMonthlyPrice());
        return packRepository.save(pack);
    }

    public void deletePack(Long id) {
        Pack pack = packRepository.findById(id).orElseThrow(() -> new RuntimeException("Pack not found"));
        packRepository.delete(pack);
    }
}