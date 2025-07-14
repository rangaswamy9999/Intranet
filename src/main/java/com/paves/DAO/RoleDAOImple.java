package com.paves.DAO;

import com.paves.Entity.Role;
import com.paves.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDAOImple implements RoleDAO
{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void insertRole(Role role) {
        roleRepository.save(role);
    }

}
