package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.*;
import com.teamVoid.healthRecord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "get/{username}")
    @PreAuthorize("authentication.principal == #username || hasAnyAuthority('ROOT_ADMIN','HOSP_ADMIN','DOCTOR')")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @GetMapping(path = "get/user/all")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN')")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "add/user")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN','HOSP_ADMIN')")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping(path = "update/{username}/password")
    @PreAuthorize("authentication.principal == #username || hasAnyAuthority('ROOT_ADMIN','HOSP_ADMIN')")
    public String updatePassword(@PathVariable String username, @RequestBody User user){
        return userService.updatePassword(username, user);
    }

    @PutMapping(path = "update/{username}/profile")
    @PreAuthorize("authentication.principal == #username")
    public String updateProfile(@PathVariable String username, @RequestBody Profile profile){
        return userService.updateProfile(username, profile);
    }

    @PutMapping(path = "update/{username}/image")
    @PreAuthorize("authentication.principal == #username")
    public String updateImage(@PathVariable String username, @RequestParam("image") MultipartFile image) throws IOException {
        return userService.updateImage(username, image);
    }

    @PutMapping(path = "update/{username}/doctor")
    @PreAuthorize("authentication.principal == #username && hasAnyAuthority('DOCTOR')")
    public String updateDoctor(@PathVariable String username, @RequestBody Doctor doctor) {
        return userService.updateDoctor(username, doctor);
    }

    @PutMapping(path = "update/{username}/patient")
    @PreAuthorize("authentication.principal == #username && hasAnyAuthority('PATIENT')")
    public String updatePatient(@PathVariable String username, @RequestBody Patient patient) {
        return userService.updatePatient(username, patient);
    }

    @DeleteMapping(path = "delete/user/{username}")
    @PreAuthorize("authentication.principal == #username || hasAnyAuthority('ROOT_ADMIN')")
    public String deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }

    @DeleteMapping(path = "delete/{username}/image")
    @PreAuthorize("authentication.principal == #username || hasAnyAuthority('ROOT_ADMIN')")
    public String deleteImage(@PathVariable String username) {
        return userService.deleteImage(username);
    }
}
