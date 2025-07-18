package com.paves.Controller;


import com.paves.Entity.User;
import com.paves.Service.MyUserDetailsService;
import com.paves.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MyUserDetailsService customMyUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user)
    {
        return userService.userRegistration(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.login(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
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