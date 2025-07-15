package com.paves.Controller;


import com.paves.Entity.User;
import com.paves.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.userRegistration(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
       return userService.updateUserByusingId(id,user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("/add_role/{userId}/{roleId}")
    public ResponseEntity<String> assignRoleToUser(@PathVariable String userId, @PathVariable Long roleId) {
        return userService.assignRoleToUser(userId,roleId);
    }

    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<String> removeRoleToUser(@PathVariable String userId, @PathVariable Long roleId)
    {
        return userService.removeRoleToUser(userId,roleId);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<User>> getAllRolesAssingedToUsers()
    {
        return userService.getAllUsersWithRoles();
    }

}