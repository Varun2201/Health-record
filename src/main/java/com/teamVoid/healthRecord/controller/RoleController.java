package com.teamVoid.healthRecord.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamVoid.healthRecord.model.Role;
import com.teamVoid.healthRecord.service.RoleService;

@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "get/roles")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN')")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @PostMapping(path = "add/role")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN','HOSP_ADMIN')")
    public String addRole(@RequestBody Role role) {
        role.setRoleName(role.getRoleName().toUpperCase());
        @SuppressWarnings("unchecked")
		List<SimpleGrantedAuthority> collection = (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean flag = false;
        for (SimpleGrantedAuthority i:collection) {
            if (i.getAuthority().equals("ROOT_ADMIN")) {
                flag = true;
                break;
            }
        }
        if ((role.getRoleName().equals("HOSP_ADMIN") || role.getRoleName().equals("ROOT_ADMIN")) && !flag) {
            throw new IllegalArgumentException("you cannot add higher authorities");
        }
        return roleService.addRole(role);
    }

    @DeleteMapping(path = "delete/{username}/{roleName}")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN','HOSP_ADMIN')")
    public String deleteRole(@PathVariable("username") String username, @PathVariable("roleName") String roleName) {
        roleName = roleName.toUpperCase(Locale.ROOT);
        @SuppressWarnings("unchecked")
		List<SimpleGrantedAuthority> collection = (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean flag = false;
        for (SimpleGrantedAuthority i:collection) {
            if (i.getAuthority().equals("ROOT_ADMIN")) {
                flag = true;
                break;
            }
        }
        if ((roleName.equals("HOSP_ADMIN") || roleName.equals("ROOT_ADMIN")) && !flag) {
            throw new IllegalArgumentException("you cannot delete higher authorities");
        }
        return roleService.deleteRole(username, roleName);
    }
    @GetMapping(path = "get/roles/{roleName}")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN')")
    public List<Role> getRolesByRoleName(@PathVariable String roleName) {
        return roleService.getRolesByRoleName(roleName.toUpperCase());
    }
}
