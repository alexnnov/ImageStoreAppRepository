package com.imagestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.imagestore.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	
	User findByEmail(String email);
}
