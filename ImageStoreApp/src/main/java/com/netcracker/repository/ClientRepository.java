package com.netcracker.repository;

import com.netcracker.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer>{
}
