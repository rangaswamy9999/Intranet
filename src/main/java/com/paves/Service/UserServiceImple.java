package com.paves.Service;

import com.paves.Config.JwtService;
import com.paves.DAO.UserDAO;
import com.paves.Entity.User;
import com.paves.Exception.UserExceptionHandler;
import com.paves.Repository.UserRepository;
import io.jsonwebtoken.io.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author paves
 */
@Service
public class UserServiceImple implements UserService
{

    @Autowired
    UserDAO userDAO;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public ResponseEntity<User> userRegistration(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User user1 = userDAO.userRegistration(user);
        if (user1 != null) {
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user1, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String login(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUserName());
        } else {
            return "fail";
        }
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

    @Override
    public ResponseEntity<List<User>> getAllUsersWithRoles() {
        List<User> users = userDAO.getAllUsersWithRoles();
        return users.size()!=0? new ResponseEntity<>(users,HttpStatus.FOUND): new ResponseEntity<>(users,HttpStatus.NOT_FOUND);
    }
}