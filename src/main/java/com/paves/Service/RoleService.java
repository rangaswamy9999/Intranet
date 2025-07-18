package com.paves.Service;

import com.paves.Entity.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<Role> roleRegister(Role role);
    ResponseEntity<Role> removeRole(long roleId);
    ResponseEntity<Role> updateRoleByRoleID(long roleId,Role role);
    Role assignPermissionToRole(Long roleId, Long permissionId);
    Role removePermissionFromRole(Long roleId, Long permissionId);

}
