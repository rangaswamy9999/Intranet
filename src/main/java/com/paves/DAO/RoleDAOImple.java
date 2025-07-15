package com.paves.DAO;

import com.paves.Entity.Role;
import com.paves.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleDAOImple implements RoleDAO
{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role insertRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role deleteByRoleId(long roleId) {
        return roleRepository.findById(roleId).map(
                (role)->
                {
                    System.out.println(role.getUsers());
//                    roleRepository.deleteById(roleId);
                    return role;
                }
        ).orElse(null);
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
