package com.example.backend.controller;

import java.util.List;

@RestController
@RequestMapping("/api/packs")
public class PackController {

    @Autowired
    private com.example.backend.service.PackService packService;

    @PostMapping
    public ResponseEntity<com.example.backend.model.Pack> addPack(@RequestBody com.example.backend.model.Pack pack) {
        com.example.backend.model.Pack createdPack = packService.addPack(pack);
        return ResponseEntity.ok(createdPack);
    }

    @GetMapping
    public ResponseEntity<List<com.example.backend.model.Pack>> getAllPacks() {
        List<com.example.backend.model.Pack> packs = packService.getAllPacks();
        return ResponseEntity.ok(packs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.backend.model.Pack> getPackById(@PathVariable Long id) {
        com.example.backend.model.Pack pack = packService.getPackById(id);
        return ResponseEntity.ok(pack);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.backend.model.Pack> updatePack(@PathVariable Long id, @RequestBody com.example.backend.model.Pack pack) {
        com.example.backend.backend.model.Pack updatedPack = packService.updatePack(id, pack);
        return ResponseEntity.ok(updatedPack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        packService.deletePack(id);
        return ResponseEntity.noContent().build();
    }
}