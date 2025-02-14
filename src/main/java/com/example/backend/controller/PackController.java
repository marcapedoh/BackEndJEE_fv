package com.example.backend.controller;

import com.example.backend.model.Pack;
import com.example.backend.service.PackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "packs")
public class PackController {

    private final PackService packService;

    public PackController(PackService packService) {
        this.packService = packService;
    }

    @PostMapping
    public ResponseEntity<Pack> addPack(@RequestBody Pack pack) {
        Pack createdPack = packService.addPack(pack);
        return ResponseEntity.ok(createdPack);
    }

    @GetMapping
    public ResponseEntity<List<Pack>> getAllPacks() {
        return ResponseEntity.ok(packService.getAllPacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pack> getPackById(@PathVariable Long id) {
        return ResponseEntity.ok(packService.getPackById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable Long id, @RequestBody Pack pack) {
        return ResponseEntity.ok(packService.updatePack(id, pack));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        packService.deletePack(id);
        return ResponseEntity.noContent().build();
    }

}