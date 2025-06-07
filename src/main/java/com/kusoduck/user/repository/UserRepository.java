package com.kusoduck.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusoduck.user.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, String> {
}
