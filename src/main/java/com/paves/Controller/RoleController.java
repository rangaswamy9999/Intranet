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

    @DeleteMapping("remove_role/{roleId}")
    public ResponseEntity<Role> removeRole(@PathVariable long roleId)
    {
        return roleService.removeRole(roleId);
    }
}
