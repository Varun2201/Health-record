package com.teamVoid.healthRecord.repository;

import com.teamVoid.healthRecord.model.Role;
import com.teamVoid.healthRecord.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> getRolesByUsername(String username);
    List<Role> getRolesByRoleName(String roleName);

    User getUserByUsername(String username);
}
