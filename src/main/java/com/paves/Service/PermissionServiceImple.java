package com.paves.Service;

import com.paves.DAO.PermissionDAO;
import com.paves.Entity.Permission;
import com.paves.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PermissionServiceImple implements PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public Permission create(Permission permission) {
        return permissionDAO.save(permission);
    }

    @Override
    public Permission update(Long id, Permission permission) {
        return permissionDAO.update(id, permission);
    }

    @Override
    public Permission get(Long id) {
        return permissionDAO.getById(id);
    }

    @Override
    public List<Permission> getAll() {
        return permissionDAO.getAll();
    }

    @Override
    public void delete(Long id) {
        permissionDAO.deleteByPermissionId(id);
    }
}
