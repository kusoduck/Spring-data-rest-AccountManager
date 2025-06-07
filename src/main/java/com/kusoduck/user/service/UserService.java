package com.kusoduck.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.kusoduck.user.entity.User;
import com.kusoduck.user.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void registerUser(String username, String password) {
        User user = new User();
        user.setName(username);
        String encryptPassword =  BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword("{bcrypt}" + encryptPassword);
        user.setActive(1);
        userRepository.save(user);

        // add role in t_role
        jdbcTemplate.update("insert into t_role (name, role) values (?, ?)", username, "ROLE_EMPLOYEE");
    }
}