package com.teamVoid.healthRecord.service;

import com.teamVoid.healthRecord.model.Role;
import com.teamVoid.healthRecord.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final HashSet<String> roleSet = new HashSet<>(Arrays.asList("ROOT_ADMIN", "HOSP_ADMIN", "DOCTOR", "PATIENT"));

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public String addRole(Role role) {
        if (!roleSet.contains(role.getRoleName())) throw new IllegalArgumentException("invalid role | available - (ROOT_ADMIN, HOSP_ADMIN, PATIENT, DOCTOR)");
        List<Role> roles = roleRepository.getRolesByUsername(role.getUsername());
        for (Role i:roles) {
            if (i.getRoleName().equals(role.getRoleName())) throw new IllegalArgumentException(role.getUsername() + " already authorized with " + role.getRoleName());
        }
        try {
            roleRepository.save(role);
        } catch (Exception e) {
            throw new IllegalArgumentException(role.getUsername() + " not found");
        }
        return role.getUsername() + " authorized with " + role.getRoleName();
    }

    public String deleteRole(String username, String roleName) {
        roleName = roleName.toUpperCase(Locale.ROOT);
        if (!roleSet.contains(roleName)) throw new IllegalArgumentException("invalid role | available - (ADMIN, PATIENT, DOCTOR)");
        List<Role> roles = roleRepository.getRolesByUsername(username);
        for (Role i:roles) {
            if (i.getRoleName().equals(roleName)) {
                roleRepository.delete(i);
                return username + " unauthorized with " + roleName;
            }
        }
        throw new IllegalArgumentException(username + " or " + roleName + " not applicable");
    }
    public List<Role> getRolesByRoleName(String roleName) {
        return roleRepository.getRolesByRoleName(roleName);
    }
}
