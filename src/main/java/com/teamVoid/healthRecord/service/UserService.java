package com.teamVoid.healthRecord.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teamVoid.healthRecord.model.Doctor;
import com.teamVoid.healthRecord.model.Patient;
import com.teamVoid.healthRecord.model.Profile;
import com.teamVoid.healthRecord.model.User;
import com.teamVoid.healthRecord.repository.UserRepository;
import com.teamVoid.healthRecord.security.ApplicationUserDetails;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username + " not found");
        return new ApplicationUserDetails(user);
    }

    public User getUser(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) throw new IllegalArgumentException(username + " not found");
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        User repoUser = userRepository.getUserByUsername(user.getUsername());
        if (repoUser != null) throw new IllegalArgumentException(user.getUsername() + " already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getUsername() + " added";
    }

    public String updatePassword(String username, User user) {
        User repoUser = userRepository.getUserByUsername(username);
        if (repoUser == null) throw new IllegalArgumentException(username + " not found");
        repoUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(repoUser);
        return repoUser.getUsername() + " updated";
    }

    public String updateProfile(String username, Profile profile) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) throw new IllegalArgumentException(username + " not found");
        if (user.getProfile() != null) {
            if (user.getProfile().getImage() != null) profile.setImage(user.getProfile().getImage());
        }
        user.setProfile(profile);
        userRepository.save(user);
        return "profile updated for " + username;
    }

    public String updateImage(String username, MultipartFile image) throws IOException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) throw new IllegalArgumentException(username + " not found");
        Profile profile = (user.getProfile() == null) ? new Profile() : user.getProfile();
        profile.setImage(image.getBytes());
        user.setProfile(profile);
        userRepository.save(user);
        return "profile image updated for " + username;
    }

    public String updateDoctor(String username, Doctor doctor) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) throw new IllegalArgumentException(username + " not found");
        user.setDoctor(doctor);
        userRepository.save(user);
        return "doctor details updated for " + username;
    }

    public String updatePatient(String username, Patient patient) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) throw new IllegalArgumentException(username + " not found");
        user.setPatient(patient);
        userRepository.save(user);
        return "patient details updated for " + username;
    }

    public String deleteUser(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) throw new IllegalArgumentException(username + " not found");
        userRepository.delete(user);
        return username + " deleted";
    }

    public String deleteImage(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) throw new IllegalArgumentException(username + " not found");
        Profile profile = user.getProfile();
        profile.setImage(null);
        userRepository.save(user);
        return "profile image deleted for " + username;
    }
}
