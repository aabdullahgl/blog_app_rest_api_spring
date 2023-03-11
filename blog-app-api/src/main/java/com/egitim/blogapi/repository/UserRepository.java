package com.egitim.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egitim.blogapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserMail(String email);
}
