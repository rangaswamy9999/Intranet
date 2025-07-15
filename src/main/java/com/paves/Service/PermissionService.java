package com.paves.Service;

import com.paves.Entity.Permission;
import java.util.List;

public interface PermissionService {
    Permission create(Permission permission);
    Permission update(Long id, Permission permission);
    Permission get(Long id);
    List<Permission> getAll();
    void delete(Long id);
}