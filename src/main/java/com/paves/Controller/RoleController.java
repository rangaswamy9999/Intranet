package com.paves.Controller;


import com.paves.Entity.Role;
import com.paves.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/registration")
    public ResponseEntity<Role> roleRegister(@RequestBody Role role)
    {
        return roleService.roleRegister(role);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Role> removeRole(@PathVariable long roleId)
    {
        return roleService.removeRole(roleId);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRoleById(@PathVariable long roleId,@RequestBody Role role)
    {
        return roleService.updateRoleByRoleID(roleId,role);
    }
    @PostMapping("/{roleId}/{permissionId}")
    public ResponseEntity<Role> assignPermissionToRole(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {
        Role updatedRole = roleService.assignPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}/{permissionId}")
    public ResponseEntity<Role> removePermissionFromRole(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {
        Role updatedRole = roleService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }

}
