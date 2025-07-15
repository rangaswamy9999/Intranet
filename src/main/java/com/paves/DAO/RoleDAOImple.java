package com.paves.DAO;

import com.paves.Entity.Role;
import com.paves.Entity.User;
import com.paves.Exception.RoleExceptionHandler;
import com.paves.Repository.RoleRepository;
import com.paves.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;

@Component
public class RoleDAOImple implements RoleDAO
{
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Role insertRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role deleteByRoleId(long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleExceptionHandler("Role not found"));

        long userCount = userRepository.countUsersByRoleId(roleId);

        if (userCount > 0) {
            throw new RoleExceptionHandler("Role is assigned to one or more users and cannot be deleted");
        }

        roleRepository.deleteById(roleId);
        return role;
    }


    @Override
    public Role updateByRoleId(long roleId,Role role) {
        return roleRepository.findById(roleId).map(
                (rolel)->
                {
                    roleRepository.save(role);
                    return role;
                }
        ).orElse(null);
    }

}
