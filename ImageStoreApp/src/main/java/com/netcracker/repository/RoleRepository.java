package com.imagestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.imagestore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
