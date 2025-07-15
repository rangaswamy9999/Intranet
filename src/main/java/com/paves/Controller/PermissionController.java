package com.paves.Controller;

import com.paves.Entity.Permission;
import com.paves.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/create")
    public ResponseEntity<Permission> create(@RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.create(permission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permission> update(@PathVariable Long id, @RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.update(id, permission));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> get(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAll() {
        return ResponseEntity.ok(permissionService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return ResponseEntity.ok("Permission deleted successfully");
    }
}