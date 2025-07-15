package com.paves.Service;

import com.paves.DAO.RoleDAO;
import com.paves.Entity.Role;
import com.paves.Exception.RoleExceptionHandler;
import com.paves.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImple implements RoleService{

    @Autowired
    RoleDAO roleDAO;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public ResponseEntity<Role> roleRegister(Role role) {
        Role roleRes = roleDAO.insertRole(role);
        if(roleRes != null)
        {
            return new ResponseEntity<>(roleRes,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(roleRes,HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Role> removeRole(long roleId) {
        Role role = roleDAO.deleteByRoleId(roleId);
        if(role != null)
        {
            return new ResponseEntity<>(role,HttpStatus.ACCEPTED);
        }
        else{
            throw  new RoleExceptionHandler("Role Not Found");
        }
    }


    @Override
    public ResponseEntity<Role> updateRoleByRoleID(long roleId, Role role) {
        Role roleRes = roleDAO.updateByRoleId(roleId,role);
        if(roleRes != null)
        {
            return new ResponseEntity<>(roleRes,HttpStatus.ACCEPTED);
        }
        else {
            throw new RoleExceptionHandler("Role Id Not Found");
        }
    }
}
