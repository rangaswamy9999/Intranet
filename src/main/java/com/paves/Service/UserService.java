package com.paves.Service;

import com.paves.Entity.User;
import jakarta.transaction.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<User> userRegistration(User user);
    ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<User> getUserById(String id);
    ResponseEntity<User> updateUserByusingId(String id,User user);
    ResponseEntity<User> deleteUserById(String id);
    ResponseEntity<String> assignRoleToUser(String userId, Long roleId);
    ResponseEntity<String> removeRoleToUser(String userId, Long roleId);


}
