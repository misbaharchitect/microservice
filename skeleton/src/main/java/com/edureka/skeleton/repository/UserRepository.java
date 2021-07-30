package com.edureka.skeleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.skeleton.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
