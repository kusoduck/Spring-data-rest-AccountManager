package com.kusoduck.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kusoduck.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}