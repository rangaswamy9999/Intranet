package com.paves.DAO;

import com.paves.Entity.Role;

public interface RoleDAO {
    Role insertRole(Role role);
    Role deleteByRoleId(long roleId);
    Role updateByRoleId(long roleId,Role role);
    Role assignPermissionToRole(Long roleId, Long permissionId);
    Role removePermissionFromRole(Long roleId, Long permissionId);
}
