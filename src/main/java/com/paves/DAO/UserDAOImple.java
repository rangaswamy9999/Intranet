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
        Optional<User> optionalUser = userRepository.findByUserId(id);
        if(optionalUser.isPresent())
        {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public User updateUserByusingId(String id, User user) {
        if(getUserById(id) != null)
        {
            userRegistration(user);
            return user;
        }
        return null;
    }

    @Transactional
    @Override
    public User deleteUserById(String id) {
        Optional<User> optionalUser = userRepository.findByUserId(id);
        if(optionalUser.isPresent())
        {
            return optionalUser.get();
        }
        return null;
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
}
