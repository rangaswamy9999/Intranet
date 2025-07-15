package com.paves.DAO;

import com.paves.Entity.Permission;
import java.util.List;

public interface PermissionDAO {
    Permission save(Permission permission);
    Permission update(Long id, Permission permission);
    Permission getById(Long id);
    List<Permission> getAll();
//    void delete(Long id);
    Permission deleteByPermissionId(Long id);
}