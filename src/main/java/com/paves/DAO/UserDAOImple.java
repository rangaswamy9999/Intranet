package com.paves.DAO;

import com.paves.Entity.Role;
import com.paves.Entity.User;
import com.paves.Exception.UserExceptionHandler;
import com.paves.Repository.RoleRepository;
import com.paves.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDAOImple implements UserDAO{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public User userRegistration(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findByUserId(id).orElse(null);
    }

    @Override
    public User updateUserByusingId(String id, User user) {
        return getUserById(id) != null ? userRegistration(user) : null;
    }

    @Transactional
    @Override
    public User deleteUserById(String id) {
        return userRepository.findByUserId(id)
                .map(user -> {
                    userRepository.deleteByUserId(id);
                    return user;
                })
                .orElse(null);
    }


    @Override
    public String assignRoleToUser(String userId, Long roleId) {
        Optional<User> userOpt = userRepository.findByUserId(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            User user = userOpt.get();
            Role role = roleOpt.get();
            user.getRoles().add(role);
            userRepository.save(user);

            return "Role Assigned to User";
        } else {
            return "";
        }
    }

    @Override
    public String removeRoleToUser(String userId, Long roleId) {
        Optional<User> userOpt = userRepository.findByUserId(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            User user = userOpt.get();
            Role role = roleOpt.get();
            user.getRoles().remove(role);
            userRepository.save(user);
            return "Role Removed to User ";
        } else {
            return "";
        }
    }

    @Override
    public List<User> getAllUsersWithRoles() {
        List<User> users = userRepository.findAll();
        List<User> userRoles = users.stream()
                .filter(user -> user.getRoles() != null && !user.getRoles().isEmpty())
                .collect(Collectors.toList());

        return userRoles;
    }

}
