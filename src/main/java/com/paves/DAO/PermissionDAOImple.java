package com.paves.DAO;


import com.paves.Entity.Permission;
import com.paves.Exception.PermissionExceptionHandler;
import com.paves.Repository.PermissionRepository;
import com.paves.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PermissionDAOImple implements PermissionDAO {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Long id, Permission permission) {
        Optional<Permission> existing = permissionRepository.findById(id);
        if (existing.isPresent()) {
            Permission p = existing.get();
            p.setPName(permission.getPName());
            p.setPDescription(permission.getPDescription());
            return permissionRepository.save(p);
        }
        throw new PermissionExceptionHandler("Permission not found");
    }

    @Override
    public Permission getById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionExceptionHandler("Permission not found"));
    }

    @Override
    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }

//    @Override
//    public void delete(Long id) {
//        if (!permissionRepository.existsById(id)) {
//            throw new PermissionExceptionHandler("Permission not found");
//        }
//        permissionRepository.deleteById(id);
//    }

    @Override
    public Permission deleteByPermissionId(Long id) {
       Optional<Permission> permission=permissionRepository.findById(id);
       if(permission.isPresent())
       {
           long roleCount = roleRepository.countRolesByPermissionId(id);
           if (roleCount > 0) {
               throw new PermissionExceptionHandler("Permission is assigned to roles and cannot be deleted.");
           }
           else {
               permissionRepository.deleteById(id);
               return permission.get();
           }

       }
       else {
           throw  new PermissionExceptionHandler("Permission Not Found");
       }
    }


}
