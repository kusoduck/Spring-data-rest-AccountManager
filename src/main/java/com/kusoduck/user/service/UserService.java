package com.kusoduck.user.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kusoduck.user.entity.AppUser;
import com.kusoduck.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    public UserService(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void registerUser(String username, String password) {
        AppUser user = new AppUser();
        user.setName(username);
        user.setPassword("{noop}" + password);
        user.setActive(1);
        userRepository.save(user);
        jdbcTemplate.update("insert into t_role (name, role) values (?, ?)", username, "ROLE_EMPLOYEE");
    }
}
