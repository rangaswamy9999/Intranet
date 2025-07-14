package com.paves.Service;

import com.paves.DAO.RoleDAO;
import com.paves.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImple implements RoleService{

    @Autowired
    RoleDAO roleDAO;

    @Override
    public void roleRegister(Role role) {
        roleDAO.insertRole(role);
    }
}
