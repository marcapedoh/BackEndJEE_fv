package com.example.backend.controller;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private com.example.backend.service.UserService userService;

    @PostMapping
    public ResponseEntity<com.example.backend.model.User> createUser(@RequestBody com.example.backend.model.User user) {
        com.example.backend.model.User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<com.example.backend.model.User>> getAllUsers() {
        List<com.example.backend.model.User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.backend.model.User> getUserById(@PathVariable Long id) {
        com.example.backend.model.User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.backend.model.User> updateUser(@PathVariable Long id, @RequestBody com.example.backend.model.User user) {
        com.example.backend.model.User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
