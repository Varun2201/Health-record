package com.teamVoid.healthRecord.repository;

import com.teamVoid.healthRecord.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String username);
}
