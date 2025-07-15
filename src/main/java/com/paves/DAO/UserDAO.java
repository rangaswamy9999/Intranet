package com.paves.DAO;

import com.paves.Entity.User;

import java.util.List;

public interface UserDAO
{
    User userRegistration(User user);
    List<User> getAllUsers();
    User getUserById(String id);
    User updateUserByusingId(String id,User user);
    User deleteUserById(String id);
    String assignRoleToUser(String userId, Long roleId);
    String removeRoleToUser(String userId,Long roleId);
    List<User> getAllUsersWithRoles();
}
