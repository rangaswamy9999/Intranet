package com.paves.Repository;

import com.paves.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT COUNT(r) FROM Role r JOIN r.permissions p WHERE p.pId = :permissionId")
    long countRolesByPermissionId(@Param("permissionId") Long permissionId);
}