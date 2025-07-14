package com.paves.DAO;

import com.paves.Entity.Role;

public interface RoleDAO {
    Role insertRole(Role role);
    Role deleteByRoleId(long roleId);
}
