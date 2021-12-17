package com.edu.dockerdbdemo.resource;

import com.edu.dockerdbdemo.model.User;
import com.edu.dockerdbdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    private final UserRepository userRepo;

    public UserResource(UserRepository empRepo) {
        this.userRepo = empRepo;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        LOGGER.info("Getting all users");
        return userRepo.findAll();
    }

    @GetMapping("/users/{empId}")
    public User getSingleUsers(@PathVariable Long empId) {
        LOGGER.info("Getting single user: {}", empId);
        Optional<User> emp = userRepo.findById(empId);

        return emp.orElse(new User());
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User emp) {
        LOGGER.info("Saving user: {}", emp);
        return userRepo.save(emp);
    }

    @DeleteMapping("/users/{empId}")
    public void deleteUser(Long empId) {
        LOGGER.info("Deleting user: {}", empId);
        userRepo.deleteById(empId);
    }










}
