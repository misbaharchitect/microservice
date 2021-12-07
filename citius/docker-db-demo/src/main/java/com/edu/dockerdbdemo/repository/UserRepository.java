package com.edu.dockerdbdemo.repository;

import com.edu.dockerdbdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
