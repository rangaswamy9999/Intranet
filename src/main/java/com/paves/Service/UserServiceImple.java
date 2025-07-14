package com.paves.Service;


import com.paves.DAO.UserDAO;
import com.paves.Entity.User;
import com.paves.Exception.UserExceptionHandler;
import com.paves.Repository.UserRepository;
import jakarta.transaction.Status;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class UserServiceImple implements UserService
{

    @Autowired
    UserDAO userDAO;

    @Override
    public ResponseEntity<User> userRegistration(User user) {
        User user1 = userDAO.userRegistration(user);
        if (user1 != null) {
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user1, HttpStatus.BAD_REQUEST);
    }
    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = userDAO.getAllUsers();
        if(user.size() != 0)
        {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<User> getUserById(String id) {
        User user = userDAO.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        else {
            throw  new UserExceptionHandler("User not found with ID: " + id);
        }
    }

    @Override
    public ResponseEntity<User> updateUserByusingId(String id, User user) {
        if (userDAO.getUserById(id) != null) {
            user.setUserId(id);
            return new ResponseEntity<>(userDAO.updateUserByusingId(id,user), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<User> deleteUserById(String id) {
        User user = userDAO.deleteUserById(id);
        if(user != null)
        {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            throw new UserExceptionHandler("User not found with ID: " + id);
        }

    }

    @Override
    public ResponseEntity<String> assignRoleToUser(String userId, Long roleId) {
        String msg =userDAO.assignRoleToUser(userId,roleId);
        if(msg.isEmpty())
        {
            throw new UserExceptionHandler("Role Assignment Failure");
        }
        else {
            return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<String> removeRoleToUser(String userId, Long roleId) {
        String msg =userDAO.removeRoleToUser(userId,roleId);
        if(msg.isEmpty())
        {
            throw new UserExceptionHandler("Role Removal Failure");
        }
        else {
            return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
        }
    }
}