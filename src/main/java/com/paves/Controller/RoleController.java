package com.paves.Controller;


import com.paves.Entity.Role;
import com.paves.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/registration")
    public void roleRegister(@RequestBody Role role)
    {
        roleService.roleRegister(role);
    }
}
